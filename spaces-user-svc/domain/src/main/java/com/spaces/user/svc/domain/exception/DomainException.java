package com.spaces.user.svc.domain.exception;

import com.spaces.user.svc.domain.validation.Error;

import java.util.Collection;
import java.util.List;

public class DomainException extends NostackTrace{
    private final List<Error> errors;

    private DomainException(final String message, List<Error> errors) {
        super(message);
        this.errors = errors;
    }

    public static DomainException with(final List<Error> errors) {
        return new DomainException("",errors);
    }

    public Collection<? extends Error> getErrors() {
        return errors;
    }

    public static DomainException with(final Error error) {
        return new DomainException(error.message(),List.of(error));
    }
}
