package com.spaces.user.svc.infrastructure.api.controller;

import com.spaces.user.svc.application.user.CreateUserUseCase;
import com.spaces.user.svc.application.user.UserCreateCommand;
import com.spaces.user.svc.application.user.UserCreatedOutput;
import com.spaces.user.svc.domain.users.Tellphone;
import com.spaces.user.svc.domain.validation.handler.Notification;
import com.spaces.user.svc.infrastructure.api.UserAPI;
import com.spaces.user.svc.infrastructure.users.models.CreateUserApiInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.function.Function;

@RestController
public class UserController implements UserAPI {

    private final CreateUserUseCase createUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @Override
    public ResponseEntity<?> createUser(CreateUserApiInput createUserApiInput) {
        var userCreateCommand = UserCreateCommand.with(
                createUserApiInput.username(),
                createUserApiInput.password(),
                createUserApiInput.email(),
                createUserApiInput.birthdayDate(),
                createUserApiInput.gender(),
                Tellphone.fromString(createUserApiInput.tellphone())

        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);


        final Function<UserCreatedOutput, ResponseEntity<?>> onSuccess = userCreatedOutput ->
                ResponseEntity.created(URI.create("/users/" + userCreatedOutput.userID().getValue())).build();

        return this.createUserUseCase.execute(userCreateCommand)
                .fold(onError, onSuccess);

    }

}
