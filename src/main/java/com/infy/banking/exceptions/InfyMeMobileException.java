package com.infy.banking.exceptions;

public class InfyMeMobileException extends Exception {

    private static final long serialVersionUID = 1L;
    protected int httpStatus;
    protected String code = null;
    protected String message = null;
    protected String details = null;

    @Override
    public String getMessage() {
        return message;
    }


    public InfyMeMobileException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public InfyMeMobileException(String message) {
        super(message);
    }

    public InfyMeMobileException(String code, String message, String details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }

    public InfyMeMobileException(String code, String message, Throwable t) {
        super(t);
        this.code = code;
        this.message = message;
        this.details = t != null ? t.getMessage() : "No error details available";
    }


}
