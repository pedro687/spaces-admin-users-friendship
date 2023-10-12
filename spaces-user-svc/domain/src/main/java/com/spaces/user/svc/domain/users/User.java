package com.spaces.user.svc.domain.users;

import com.spaces.user.svc.domain.AggregateRoot;
import com.spaces.user.svc.domain.exception.DomainException;
import com.spaces.user.svc.domain.validation.ValidationHandler;

import java.time.LocalDate;

public class User extends AggregateRoot<UserID> {
    private String username;
    private String password;
    private String email;
    private LocalDate birthdayDate;
    private Gender gender;
    private Tellphone tellphone;

    private boolean active;

    private User(UserID userID, String username, String password, String email, LocalDate birthdayDate, Gender gender, Tellphone tellphone, boolean active) {
        super(userID);
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthdayDate = birthdayDate;
        this.gender = gender;
        this.tellphone = tellphone;
        this.active = active;
    }

    public static User newUser(String username,
                            String password,
                            String email,
                            LocalDate birthdayDate,
                            Gender gender,
                            Tellphone tellphone) {
        final var userId = UserID.unique();
        return new User(userId, username, password, email, birthdayDate, gender, tellphone, true);
    }

    @Override
    public void validate(ValidationHandler validationHandler) {
        var userValidator = new UserValidator(validationHandler, this);
        userValidator.validate();

        if (validationHandler.hasError()) {
            final var errs = validationHandler.getErrors();
            throw DomainException.with(errs);
        }

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(LocalDate birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Tellphone getTellphone() {
        return tellphone;
    }

    public void setTellphone(Tellphone tellphone) {
        this.tellphone = tellphone;
    }


}
