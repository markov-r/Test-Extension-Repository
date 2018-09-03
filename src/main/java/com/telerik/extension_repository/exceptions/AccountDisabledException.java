package com.telerik.extension_repository.exceptions;


import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Account has been disabled")
public class AccountDisabledException extends RuntimeException {
    public AccountDisabledException(String msg) {
        super(msg);
    }

    public AccountDisabledException(String msg, Throwable t) {
        super(msg, t);
    }
}
