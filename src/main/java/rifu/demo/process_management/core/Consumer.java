package rifu.demo.process_management.core;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rifu.demo.process_management.Commands;
import rifu.demo.process_management.event.ConsumeMessageEvent;

import java.util.concurrent.TimeUnit;

@Component
public class Consumer implements Runnable {

    @Autowired
    ProcessManagement processManagement;

    @Override
    public void run() {
        while (true) {
            System.out.println("this is consumer.");
            ConsumeMessageEvent event = new ConsumeMessageEvent(ConsumeMessageEvent.class, RandomUtils.nextInt(), Commands.MESSAGE_ARRIVED.toString(), "{\"docType\":\"EOB\"}");

            processManagement.actionPerformed(event);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
