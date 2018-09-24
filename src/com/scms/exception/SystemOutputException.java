package com.scms.exception;

public class SystemOutputException extends Exception {
    public SystemOutputException() {
    }

    public SystemOutputException(String message) {
        super(message);
    }

    public SystemOutputException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemOutputException(Throwable cause) {
        super(cause);
    }

    public SystemOutputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
