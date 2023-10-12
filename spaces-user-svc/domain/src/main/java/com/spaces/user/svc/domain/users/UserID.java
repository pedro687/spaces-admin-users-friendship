package com.spaces.user.svc.domain.users;

import com.spaces.user.svc.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class UserID extends Identifier {
    private final String value;

    private UserID(String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static UserID unique() {
        return new UserID(UUID.randomUUID().toString().toLowerCase());
    }

    public static UserID from(final String anId) {
        Objects.requireNonNull(anId, "id cannot be null");
        return new UserID(anId);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserID userID = (UserID) o;
        return Objects.equals(getValue(), userID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
