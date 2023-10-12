package com.spaces.user.svc.domain.validation;

import java.util.List;

public interface ValidationHandler {
    ValidationHandler append(Error anError);

    ValidationHandler append(ValidationHandler other);

    List<Error> getErrors();
    <T> T validate(Validation<T> aValidation);

    default boolean hasError() {
        return getErrors() != null && !getErrors().isEmpty();
    }

    interface Validation<T> {

        T validate();
    }
}
