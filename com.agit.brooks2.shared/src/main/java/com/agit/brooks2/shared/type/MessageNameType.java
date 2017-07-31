package com.agit.brooks2.shared.type;

/**
 *
 * @author bayutridewanto
 */
public enum MessageNameType {
  APPROVED("APPROVED"),
    REJECTED("REJECTED"),
    ESCALATED("ESCALATED"),
    CANCELED("CANCELED");
    
    private String messageName;

    private MessageNameType(String messageName) {
        this.messageName = messageName;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }
}
