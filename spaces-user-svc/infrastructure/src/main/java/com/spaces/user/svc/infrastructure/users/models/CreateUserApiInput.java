package com.spaces.user.svc.infrastructure.users.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spaces.user.svc.domain.users.Gender;
import com.spaces.user.svc.domain.users.Tellphone;

import java.time.LocalDate;

public record CreateUserApiInput(
        String username,
        String password,
        String email,
        @JsonProperty("birthday_date") LocalDate birthdayDate,
        Gender gender,
        String tellphone
) {
}
