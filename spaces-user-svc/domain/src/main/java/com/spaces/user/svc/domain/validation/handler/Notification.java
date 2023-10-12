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

    public static Notification create(final Error error) {
        return (Notification) new Notification(new ArrayList<>()).append(error);
    }

    public static Notification create(final Throwable error) {
        return create(new Error(error.getMessage()));
    }

    @Override
    public ValidationHandler append(Error anError) {
        this.errors.add(anError);
        return this;
    }

    @Override
    public ValidationHandler append(ValidationHandler other) {
        this.errors.addAll(other.getErrors());
        return this;
    }

    @Override
    public <T> T validate(final Validation<T> aValidation) {
        try {
            return aValidation.validate();
        } catch (final DomainException ex) {
            this.errors.addAll(ex.getErrors());
        } catch (final Throwable t) {
            this.errors.add(new Error(t.getMessage()));
        }
        return null;
    }

    @Override
    public List<Error> getErrors() {
        return this.errors;
    }
}
