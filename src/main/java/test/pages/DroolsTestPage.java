/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.pages;

import beans.Message;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Collection;
import java.util.Date;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.drools.definition.KnowledgePackage;
import org.drools.event.rule.DebugAgendaEventListener;
import org.drools.event.rule.DebugWorkingMemoryEventListener;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author arnaud
 */
public class DroolsTestPage extends PocMainPage {

    private static final String testExcelFileNameSample = "DecisionTablesSample.xls";
    private static final String testExcelFileNameMortgage = "ExampleMortgageLoan.xls";
    private static final String testExcelMessage = "ExampleMessage.xls";
    private static final String helloDRL = "HelloWorld.drl";
    private static final String byeDRL = "GoodBye.drl";
    final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
    private static final Logger logger = LoggerFactory.getLogger(DroolsTestPage.class);

    // tries ... Something
    public DroolsTestPage(PageParameters parameters) throws Exception {
        super(parameters);

        long initTime = new Date().getTime();

        logger.error("BEFORE INIT !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + (new Date()).getTime());
        // Gets DRL From XLS
        InputStream xlsStream = ResourceFactory.newClassPathResource(
                testExcelMessage, DroolsTestPage.class).getInputStream();
        SpreadsheetCompiler compiler = new SpreadsheetCompiler();
        String testDrlString = compiler.compile(xlsStream, InputType.XLS);
        logger.error(testDrlString);

        long time1 = new Date().getTime();
        logger.error("XLS compilation took :<" + (time1 - initTime) + "> ms");

        // Adds rules from the XLS
        kbuilder.add(
                ResourceFactory.newReaderResource(new StringReader(testDrlString)),
                ResourceType.DRL);

        // Adds rules from static files
        kbuilder.add(
                ResourceFactory.newClassPathResource(helloDRL, DroolsTestPage.class),
                ResourceType.DRL);
        kbuilder.add(
                ResourceFactory.newClassPathResource(byeDRL, DroolsTestPage.class),
                ResourceType.DRL);

        if (kbuilder.hasErrors()) {
            logger.error(kbuilder.getErrors().toString());
        }

        // get the compiled packages (which are serializable)
        final Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();

        // add the packages to a knowledgebase (deploy the knowledge packages).
        final KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();


        kbase.addKnowledgePackages(pkgs);





        final StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

        // setup the debug listeners

        ksession.addEventListener(new DebugAgendaEventListener());
        ksession.addEventListener(new DebugWorkingMemoryEventListener());





        logger.error("AFTER INIT !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + (new Date()).getTime());

        long time2 = new Date().getTime();
        logger.error("ksession preparation took :<" + (time2 - time1) + "> ms");
        final Message message = new Message();
        message.setMessage("Hello World");
        message.setStatus(Message.HELLO);
        ksession.insert(message);

        ksession.fireAllRules();
        long time3 = new Date().getTime();
        logger.error("Rules Execution took :<" + (time3 - time2) + "> ms");
        logger.error("AFTER EXEC !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + (new Date()).getTime());

        logger.error("BLOA !");
        logger.error(message.getMessage());



        ksession.dispose();


        add(new Label("message", "Drools Test Page"));
    }
}
