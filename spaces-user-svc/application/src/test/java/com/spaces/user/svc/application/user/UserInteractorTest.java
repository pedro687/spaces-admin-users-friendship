package com.spaces.user.svc.application.user;

import com.spaces.user.svc.domain.exception.DomainException;
import com.spaces.user.svc.domain.users.Gender;
import com.spaces.user.svc.domain.users.Tellphone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
@Tag("unitTest")
class UserInteractorTest {

    @InjectMocks
    private UserInteractor userInteractor;


    @Mock
    private UserGateway gatewayMock;

    @Test
    void givenAValidUser_whenCallsCreateUser_thenShouldReturnAValidUserCreatedOutput() {
        final var expectedTellphone = Tellphone.with("996423182", "55", "11");
        // Given
        UserCreateCommand userCreateCommand = UserCreateCommand.with(
                "pedro",
                "12345",
                "pedro@email.com",
                LocalDate.of(2002, 10, 10),
                Gender.MAN,
                Tellphone.with("996423182", "55", "11")
        );

        // When
        Mockito.when(gatewayMock.save(Mockito.any())).thenAnswer(AdditionalAnswers.returnsFirstArg());

        Assertions.assertNotNull(userInteractor);
        Assertions.assertNotNull(gatewayMock);

        UserCreatedOutput userCreatedOutput = userInteractor.execute(userCreateCommand).get();

        Assertions.assertNotNull(userCreatedOutput.userID());

        Mockito.verify(gatewayMock, Mockito.times(1))
                .save(Mockito.argThat(aUser -> {
                    return Objects.equals("pedro", aUser.getUsername())
                            && Objects.equals("12345", aUser.getPassword())
                            && Objects.equals("pedro@email.com", aUser.getEmail());
                }));
    }

    @Test
    void givenAnInvalidUser_whenCallsCreateUser_thenShouldReturnDomainException() {
        UserCreateCommand userCreateCommand = UserCreateCommand.with(
                null,
                "12345",
                "pedroemail.com",
                LocalDate.of(2020, 10, 10),
                Gender.MAN,
                Tellphone.with("996423182", "55", "11")
        );

        Assertions.assertNotNull(userInteractor);
        Assertions.assertNotNull(gatewayMock);

        final var err = Assertions.assertThrows(DomainException.class, () -> {
            userInteractor.execute(userCreateCommand).getLeft();
        });

        final var errors = err.getErrors().stream().toList();
        Assertions.assertEquals("Username is required", errors.get(0).message());
        Assertions.assertEquals("Email is required", errors.get(1).message());
        Assertions.assertEquals("Birthday is required and should be more than 18 years old", errors.get(2).message());
    }
}