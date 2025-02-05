package com.infy.banking.enums;

public enum ExceptionConstants {
    SERVER_ERROR("server.invalid"),
    AUTHENTICATION_FAILED("authentication.failed"),
    USER_NOT_FOUND("user.not.found"),
    USERID_NOT_FOUND("user.id.not.found"),
    NO_USERS_FOUND("no.users.found"),
    NO_ACCOUNTS_FOUND("no.account.found"),
    NO_ACCOUNT_IS_LINKED("no.account.is.linked"),
    INSUFFICIENT_FUNDS("insufficient.funds"),
    NO_ACTIVE_TRANSACTIONS("no.active.transactions");

    private final String type;

    private ExceptionConstants(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}

