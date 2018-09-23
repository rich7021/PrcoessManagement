package rifu.demo.process_management.event;

import java.awt.event.ActionEvent;

public class ConsumeMessageEvent extends ActionEvent {
    private String document;
    private String rule;

    public ConsumeMessageEvent (Object source, int id, String command, String document) {
        super(source, id, command);
        this.document = document;
    }

    public ConsumeMessageEvent (Object source, int id, String command, String document, String rule) {
        super(source, id, command);
        this.document = document;
        this.rule = rule;
    }

    public String getDocument () {
        return document;
    }

    public void setDocument (String document) {
        this.document = document;
    }

    public String getRule () {
        return rule;
    }

    public void setRule (String rule) {
        this.rule = rule;
    }
}
