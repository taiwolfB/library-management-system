package org.team4.libraryManagement.Presentation.Dialogs;

public class WarningMessage {
    String action;
    String message;
    Runnable then;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Runnable getFunctionality() {
        return then;
    }

    public void setFunctionality(Runnable then) {
        this.then = then;
    }

    public WarningMessage(String action, String message, Runnable then) {
        this.action = action;
        this.message = message;
        this.then = then;
    }
}
