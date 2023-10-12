package com.spaces.user.svc.domain;

import com.spaces.user.svc.domain.validation.ValidationHandler;

import java.util.Objects;

public abstract class Entity<ID extends Identifier> {
    private final ID identifier;

    protected Entity(ID identifier) {
        Objects.requireNonNull("identifier cannot be null");
        this.identifier = identifier;
    }

    public abstract void validate(ValidationHandler validationHandler);

    public ID getIdentifier() {
        return identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity<?> entity = (Entity<?>) o;
        return Objects.equals(getIdentifier(), entity.getIdentifier());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentifier());
    }
}
