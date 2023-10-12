package com.spaces.user.svc.domain.users;

import com.spaces.user.svc.domain.validation.Error;
import com.spaces.user.svc.domain.validation.ValidationHandler;
import com.spaces.user.svc.domain.validation.Validator;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class UserValidator extends Validator {
    private final User user;

    protected UserValidator(ValidationHandler validationHandler, User user) {
        super(validationHandler);
        this.user = user;
    }

    Predicate<String> predicateUsername = s -> s == null || s.length() < 4;
    Predicate<String> predicatePassword = s -> s == null || s.length() < 4;

    Predicate<String> predicateEmail = s -> s == null || s.isEmpty() || !validateEmail();

    Predicate<String> predicateBirthdayDate = s -> s == null || s.isEmpty() || !validateBirthday();

    @Override
    public void validate() {

        if (predicateUsername.test(this.user.getUsername())) {
            this.validationHandler().append(new Error("Username is required"));
        }

        if (predicatePassword.test(this.user.getPassword())) {
            this.validationHandler().append(new Error("Password is required"));
        }

        if (predicateEmail.test(this.user.getEmail())) {
            this.validationHandler().append(new Error("Email is required"));
        }

        if (predicateBirthdayDate.test(this.user.getBirthdayDate().toString())) {
            this.validationHandler().append(new Error("Birthday is required and should be more than 18 years old"));
        }

    }

    public boolean validateEmail() {
        String regex = "^(.+)@(.+)$";
        return this.user.getEmail().matches(regex);
    }

    public boolean validateBirthday() {
        Integer yearNow = LocalDate.now().getYear();
        Integer yearBirthday = this.user.getBirthdayDate().getYear();

        return yearNow - yearBirthday >= 18;
    }
}
