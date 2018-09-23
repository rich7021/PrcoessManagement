package rifu.demo.process_management.event;

import java.awt.event.ActionEvent;

public class ConsumeMessageEvent extends ActionEvent {
    private String message;

    public ConsumeMessageEvent(Object source, int id, String command, String message) {
        super(source, id, command);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
