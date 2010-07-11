/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.pages;

import beans.Message;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Collection;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.RuleBase;
import org.drools.RuleBaseConfiguration;
import org.drools.RuleBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.compiler.PackageBuilder;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.drools.definition.KnowledgePackage;
import org.drools.event.rule.DebugAgendaEventListener;
import org.drools.event.rule.DebugWorkingMemoryEventListener;
import org.drools.io.ResourceFactory;
import org.drools.rule.Package;
import org.drools.runtime.StatefulKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author arnaud
 */
public class DroolsTestPage extends PocMainPage {

    private static final String testExcelFileNameSample = "test/pages/DecisionTablesSample.xls";
    private static final String testExcelFileNameMortgage = "test/pages/ExampleMortgageLoan.xls";
    private static final String testExcelMessage = "test/pages/ExampleMessage.xls";
    final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
    private static final Logger logger = LoggerFactory.getLogger(DroolsTestPage.class);

    public DroolsTestPage(PageParameters parameters) throws Exception {
        super(parameters);

        InputStream xlsStream = DroolsTestPage.class.getClassLoader().
                getResourceAsStream(testExcelMessage);
        SpreadsheetCompiler compiler = new SpreadsheetCompiler();
        String testDrlString = compiler.compile(xlsStream, InputType.XLS);
        logger.error(testDrlString);

        // hop ?
        /*
        PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl(new StringReader(testDrlString));
        Package pkg = builder.getPackage();
        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage(pkg); /**/

        // plop
        kbuilder.add(
                ResourceFactory.newReaderResource(new StringReader(testDrlString)),
                ResourceType.DRL);



        // tries ... Something
        kbuilder.add(
                ResourceFactory.newClassPathResource("HelloWorld.drl", DroolsTestPage.class),
                ResourceType.DRL);
        kbuilder.add(
                ResourceFactory.newClassPathResource("GoodBye.drl", DroolsTestPage.class),
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



        add(new Label("message", "Drools Test Page"));

        final Message message = new Message();
        message.setMessage("Hello World");
        message.setStatus(Message.HELLO);
        ksession.insert(message);

        ksession.fireAllRules();


        logger.error("BLOA !");
        logger.error(message.getMessage());



        ksession.dispose();
    }
}
