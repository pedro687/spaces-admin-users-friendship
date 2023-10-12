package com.spaces.user.svc.application.user;

import com.spaces.user.svc.application.UseCase;
import com.spaces.user.svc.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateUserUseCase extends
        UseCase<UserCreateCommand, Either<Notification, UserCreatedOutput>> {
}
