package com.spaces.user.svc.domain.validation.handler;

import com.spaces.user.svc.domain.exception.DomainException;
import com.spaces.user.svc.domain.validation.Error;
import com.spaces.user.svc.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(Error anError) {
        throw DomainException.with(anError);
    }


    @Override
    public List<Error> getErrors() {
        return null;
    }


    @Override
    public boolean hasError() {
        return ValidationHandler.super.hasError();
    }
}
