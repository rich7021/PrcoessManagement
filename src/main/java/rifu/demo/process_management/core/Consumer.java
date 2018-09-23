package rifu.demo.process_management.core;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rifu.demo.process_management.Commands;
import rifu.demo.process_management.event.ConsumeMessageEvent;

@Component
public class Consumer implements Runnable {

    @Autowired
    ProcessManagement processManagement;

    @Override
    public void run () {
//        while (true) {
        ConsumeMessageEvent event = new ConsumeMessageEvent(ConsumeMessageEvent.class, RandomUtils.nextInt(), Commands.MESSAGE_ARRIVED.toString(),
                "{\"id\":\"" + RandomStringUtils.randomAlphabetic(5) + "\",\"docType\":\"EOB\"}");

        processManagement.actionPerformed(event);
//            try {
//                TimeUnit.SECONDS.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
