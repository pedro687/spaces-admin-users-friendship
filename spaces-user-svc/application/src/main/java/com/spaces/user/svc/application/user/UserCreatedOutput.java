package com.spaces.user.svc.application.user;

import com.spaces.user.svc.domain.users.User;
import com.spaces.user.svc.domain.users.UserID;

public record UserCreatedOutput(
        UserID userID
) {

    public static UserCreatedOutput from(final User user) {
        return new UserCreatedOutput(user.getIdentifier());
    }

    public static UserCreatedOutput from(final String userId) {
        return new UserCreatedOutput(UserID.from(userId));
    }
}
