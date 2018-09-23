package rifu.demo.process_management.core;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import rifu.demo.process_management.Commands;

import javax.annotation.PostConstruct;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class ProcessManagement implements ActionListener {
    @Autowired
    ThreadPoolTaskExecutor executor;

    @Autowired
    Consumer consumer;

    @PostConstruct
    public void init() {
        executor.submit(consumer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Commands command = Commands.valueOf(e.getActionCommand());
        if (StringUtils.equals(Commands.MESSAGE_ARRIVED.toString(), command.toString())) {
            System.out.println(Commands.MESSAGE_ARRIVED);
        }
    }
}
