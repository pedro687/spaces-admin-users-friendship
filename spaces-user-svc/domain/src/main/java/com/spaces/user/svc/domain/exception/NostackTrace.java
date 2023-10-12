package com.spaces.user.svc.domain.exception;

public class NostackTrace extends RuntimeException{
    public NostackTrace(String message) {
        super(message, null);
    }

    public NostackTrace(String message, Throwable cause) {
        super(message, cause, true, false);
    }
}
