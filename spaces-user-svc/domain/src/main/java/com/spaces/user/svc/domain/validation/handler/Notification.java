package com.spaces.user.svc.domain.validation.handler;

import com.spaces.user.svc.domain.exception.DomainException;
import com.spaces.user.svc.domain.validation.Error;
import com.spaces.user.svc.domain.validation.ValidationHandler;

import java.util.ArrayList;
import java.util.List;

public class Notification implements ValidationHandler {
    private final List<Error> errors;

    private Notification(List<Error> errors) {
        this.errors = errors;
    }

    public static Notification create() {
        return new Notification(new ArrayList<>());
    }

    public static Notification create(final Throwable error) {
        return (Notification) new Notification(new ArrayList<>()).append(new Error(error.getMessage()));
    }

    @Override
    public ValidationHandler append(Error anError) {
        this.errors.add(anError);
        return this;
    }


    @Override
    public List<Error> getErrors() {
        return this.errors;
    }


}
