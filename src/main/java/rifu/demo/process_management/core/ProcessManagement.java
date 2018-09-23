package rifu.demo.process_management.core;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import rifu.demo.process_management.Commands;
import rifu.demo.process_management.event.ConsumeMessageEvent;
import rifu.demo.process_management.process_impl.ExecuteRuleImpl;
import rifu.demo.process_management.process_impl.RuleMatchingImpl;

import javax.annotation.PostConstruct;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class ProcessManagement implements ActionListener {
    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private Consumer consumer;
    @Autowired
    private RuleMatchingImpl ruleMatching;
    @Autowired
    private ExecuteRuleImpl executeRule;

    @PostConstruct
    public void init () {
        executor.submit(consumer);
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        Commands command = Commands.valueOf(e.getActionCommand());
        ConsumeMessageEvent event = (ConsumeMessageEvent) e;

        if (StringUtils.equals(Commands.MESSAGE_ARRIVED.toString(), command.toString())) {
            ruleMatching = (RuleMatchingImpl) applicationContext.getBean("ruleMatchingImpl", event.getDocument());
            executor.submit(ruleMatching);
        } else if (StringUtils.equals(Commands.RULES_MATCHED.toString(), command.toString())) {
            executeRule = (ExecuteRuleImpl) applicationContext.getBean("executeRuleImpl", event.getRule(), event.getDocument());
            executor.submit(executeRule);
        } else if (StringUtils.equals(Commands.EXECUTE_RULE.toString(), command.toString())) {
            System.out.println("Result: " + event.getDocument());
            System.out.println();
        }
    }
}
