package com.spaces.user.svc.application.user;

import com.spaces.user.svc.domain.users.Gender;
import com.spaces.user.svc.domain.users.Tellphone;

import java.time.LocalDate;

public record UserCreateCommand(
        String username,
        String password,
        String email,
        LocalDate birthdayDate,
        Gender gender,
        Tellphone tellphone
) {
    public static UserCreateCommand with(String username,
                                         String password,
                                         String email,
                                         LocalDate birthdayDate,
                                         Gender gender,
                                         Tellphone tellphone) {
        return new UserCreateCommand(username, password, email, birthdayDate, gender, tellphone);
    }
}
