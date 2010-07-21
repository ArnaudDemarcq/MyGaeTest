/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.milleborne.engineimpl.rules;

import java.util.Collection;
import java.util.Date;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.KnowledgePackage;
import org.drools.event.rule.DebugAgendaEventListener;
import org.drools.event.rule.DebugWorkingMemoryEventListener;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.krohm.milleborne.engineimpl.InternalActionExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author arnaud
 */
public class DroolsExecutionSolver {

    private static final Logger logger = LoggerFactory.getLogger(DroolsExecutionSolver.class);
    private static final String testDrl = "HelloWorld.drl";
    private static final String stepsDefinitionDrl = "StepsDefinition.drl";
    private final KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();

    public DroolsExecutionSolver() {

        final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        // Adds rules from static files to kbase
        kbuilder.add(ResourceFactory.newClassPathResource(testDrl,
                DroolsExecutionSolver.class), ResourceType.DRL);
        kbuilder.add(ResourceFactory.newClassPathResource(stepsDefinitionDrl,
                DroolsExecutionSolver.class), ResourceType.DRL);
        if (kbuilder.hasErrors()) {
            logger.error(kbuilder.getErrors().toString());
        }
        final Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();
        kbase.addKnowledgePackages(pkgs);

    }

    public void performExecution(InternalActionExecutionContext executionData) {
        logger.error("Executing :<" + executionData.toString() + ">");

        // sets up ksession
        final StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        //ksession.addEventListener(new DebugAgendaEventListener());
        //ksession.addEventListener(new DebugWorkingMemoryEventListener());
        ksession.insert(executionData);
        // Then does ... What ever it does
        long startDate = new Date().getTime();
        ksession.fireAllRules();
        long endDate = new Date().getTime();
        logger.error("FINAL STEP IS : <" + executionData.getCurrentStep() + ">");
        logger.error("RESULTING RETURN ACTION : <" + executionData.getReturnAction() + ">");
        logger.error("Rules Execution took : <" + (endDate - startDate) + ">");
    }
}
