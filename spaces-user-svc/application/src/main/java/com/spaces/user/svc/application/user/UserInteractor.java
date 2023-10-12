package com.spaces.user.svc.application.user;

import com.spaces.user.svc.application.UseCase;
import com.spaces.user.svc.domain.users.User;
import com.spaces.user.svc.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

public class UserInteractor extends CreateUserUseCase {

    private final UserGateway userGateway;

    public UserInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public Either<Notification, UserCreatedOutput> execute(UserCreateCommand aInput) {
        final var notification = Notification.create();
        final var userAggregate = User.newUser(
                aInput.username(),
                aInput.password(),
                aInput.email(),
                aInput.birthdayDate(),
                aInput.gender(),
                aInput.tellphone()
        );

        userAggregate.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(userAggregate);
    }

    public Either<Notification, UserCreatedOutput> create(User user) {
        return API.Try(() -> this.userGateway.save(user))
                .toEither()
                .bimap(Notification::create, UserCreatedOutput::from);
    }
}
