package rifu.demo.process_management.process_impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import rifu.demo.process_management.Commands;
import rifu.demo.process_management.Process;
import rifu.demo.process_management.core.ProcessManagement;
import rifu.demo.process_management.event.ConsumeMessageEvent;

@Component
@Scope("prototype")
@Lazy(value = true)
public class RuleMatchingImpl implements Process {

    @Autowired
    private ProcessManagement processManagement;
    private String document;

    private RuleMatchingImpl () {
    }

    public RuleMatchingImpl (String document) {
        this.document = document;
    }

    @Override
    public void run () {
        String rule = "Rule-" + RandomStringUtils.randomAlphabetic(10);
        System.out.println("RuleMatchingImpl: " + document + " rule matched " + rule);
        ConsumeMessageEvent event = new ConsumeMessageEvent(ConsumeMessageEvent.class, RandomUtils.nextInt(), Commands.RULES_MATCHED.toString(),
                document, rule);
        processManagement.actionPerformed(event);
    }
}
