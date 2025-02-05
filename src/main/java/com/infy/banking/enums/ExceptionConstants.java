package com.infy.banking.enums;

import java.util.ResourceBundle;

public enum ExceptionConstants {
    SERVER_ERROR("server.invalid"),
    AUTHENTICATION_FAILED("authentication.failed"),
    USER_NOT_FOUND("user.not.found"),
    USERID_NOT_FOUND("user.id.not.found"),
    NO_USERS_FOUND("no.users.found"),
    NO_ACCOUNTS_FOUND("no.account.found"),
    NO_ACCOUNT_IS_LINKED("no.account.is.linked"),
    ACCOUNT_IS_LINKED("account.is.linked"),
    INSUFFICIENT_FUNDS("insufficient.funds"),
    NO_ACTIVE_TRANSACTIONS("no.active.transactions");

    private final String type;
    private static final ResourceBundle BUNDLE= ResourceBundle.getBundle("messages");

    private ExceptionConstants(String type) {
        this.type = type;
    }
    public String getMessage(){
        return BUNDLE.getString(type);
    }
    public String getMessage(Object... args){
        return String.format(BUNDLE.getString(type),args);
    }

    @Override
    public String toString() {
        return this.type;
    }
}

