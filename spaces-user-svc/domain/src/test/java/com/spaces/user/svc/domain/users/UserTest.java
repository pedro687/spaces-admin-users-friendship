package com.spaces.user.svc.domain.users;

import com.spaces.user.svc.domain.exception.DomainException;
import com.spaces.user.svc.domain.validation.handler.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void givenAValidUser_whenCallsCreate_thenReturnUser() {
        String expectedUsername = "Pedro";
        String expectedPassword = "12345";
        String expectedEmail = "Pedro@gmail.com";
        LocalDate expectedBbirthdayDate = LocalDate.of(2002, 10, 10);
        Gender expectedGender = Gender.MAN;
        Tellphone expectedTellphone = Tellphone.with("9973098", "55", "11");

        User user = User.newUser(expectedUsername,
                expectedPassword,
                expectedEmail,
                expectedBbirthdayDate,
                expectedGender,
                expectedTellphone);

        assertEquals(expectedUsername, user.getUsername());
        assertEquals(expectedPassword, user.getPassword());
        assertEquals(expectedEmail, user.getEmail());
        assertEquals(expectedBbirthdayDate, user.getBirthdayDate());
        assertEquals(expectedGender, user.getGender());
        assertEquals(expectedTellphone, user.getTellphone());
    }

    @Test
    void givenInvalidUser_whenCallsCreate_thenReturnDomainException() {
        String expectedUsername = null;
        String expectedPassword = null;
        String expectedEmail = "Pedrogmail.com";
        LocalDate expectedBbirthdayDate = LocalDate.of(2020, 10, 10);
        Gender expectedGender = Gender.MAN;
        Tellphone expectedTellphone = Tellphone.with("9264031291", "55", "11");

        User user = User.newUser(expectedUsername,
                expectedPassword,
                expectedEmail,
                expectedBbirthdayDate,
                expectedGender,
                expectedTellphone);

        final var error = Assertions.assertThrows(
                DomainException.class,
                () -> user.validate(Notification.create())
        );

        assertEquals("Username is required", error.getErrors().stream().toList().get(0).message());
        assertEquals("Password is required", error.getErrors().stream().toList().get(1).message());
        assertEquals("Email is required", error.getErrors().stream().toList().get(2).message());
        assertEquals("Birthday is required and should be more than 18 years old", error.getErrors().stream().toList().get(3).message());

    }

}