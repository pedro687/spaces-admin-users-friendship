package com.spaces.user.svc.infrastructure.configuration;

import com.spaces.user.svc.application.user.UserGateway;
import com.spaces.user.svc.application.user.UserInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    private final UserGateway userGateway;

    public BeanConfig(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Bean
    public UserInteractor userInteractor() {
        return new UserInteractor(userGateway);
    }
}
