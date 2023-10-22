package com.spaces.user.svc.infrastructure.users;

import com.spaces.user.svc.domain.users.Gender;
import com.spaces.user.svc.domain.users.Tellphone;
import com.spaces.user.svc.domain.users.User;
import com.spaces.user.svc.infrastructure.MySQLGatewayTest;
import com.spaces.user.svc.infrastructure.users.persistence.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@MySQLGatewayTest
class UserMySQLGatewayTest {

    @Autowired
    UserMySqlGateway userMySQLGateway;

    @Autowired
    UserRepository userRepository;

    @Test
    void givenAValidUser_whenCallsCreate_shouldReturnUserId() {
        var expectedUsername = "Pedro";
        var expectedPassword = "12345";
        var expectedEmail = "Pedro@gmail.com";
        var expectedBirthday = LocalDate.of(2002, 4, 4);
        var expectedGender = Gender.MAN;
        var expectedActive = true;
        var expectedTellphone = Tellphone.with("996403198", "55", "13");
        var expectedCountAfterCreate = 1;

        var actualUser = User.newUser(
                expectedUsername,
                expectedPassword,
                expectedEmail,
                expectedBirthday,
                expectedGender,
                expectedTellphone
        );

        Assertions.assertEquals(0, userRepository.count());
        var createdUser = userMySQLGateway.save(actualUser);

        Assertions.assertEquals(expectedCountAfterCreate, userRepository.count());
        Assertions.assertEquals(expectedUsername, createdUser.getUsername());
        Assertions.assertEquals(expectedEmail, createdUser.getEmail());
        Assertions.assertEquals(expectedBirthday, createdUser.getBirthdayDate());
        Assertions.assertEquals(expectedGender, createdUser.getGender());
        Assertions.assertEquals(expectedTellphone.toString(), createdUser.getTellphone().toString());
        Assertions.assertEquals(expectedActive, createdUser.isActive());
    }

    @Test
    void givenInvalidUser_whenCallsCreate_shouldReturnException() {

    }
}