package rifu.demo.process_management.process_impl;

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
public class ExecuteRuleImpl implements Process {
    @Autowired
    private ProcessManagement processManagement;

    private String rule;
    private String document;

    private ExecuteRuleImpl () {
    }

    public ExecuteRuleImpl (String rule, String document) {
        this.rule = rule;
        this.document = document;
    }

    @Override
    public void run () {
        System.out.println("ExecuteRuleImpl: " + document + " do " + rule);
        ConsumeMessageEvent event = new ConsumeMessageEvent(ConsumeMessageEvent.class, RandomUtils.nextInt(), Commands.EXECUTE_RULE.toString(),
                document + " : " + rule);
        processManagement.actionPerformed(event);
    }
}
