package com.spaces.user.svc.infrastructure;

import com.spaces.user.svc.infrastructure.configuration.WebServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
        SpringApplication.run(WebServerConfig.class, args);
    }

    // @Bean
    //public ApplicationRunner applicationRunner(CategoryRepository categoryRepository) {
    //  return args -> {
    //    System.out.println(categoryRepository.findAll());
    //};
    //}
}