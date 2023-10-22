package com.spaces.user.svc.infrastructure.e2e;

import com.spaces.user.svc.domain.users.Gender;
import com.spaces.user.svc.domain.users.Tellphone;
import com.spaces.user.svc.infrastructure.E2ETest;
import com.spaces.user.svc.infrastructure.users.models.CreateUserApiInput;
import com.spaces.user.svc.infrastructure.users.persistence.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.URI;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@E2ETest
@Testcontainers
public class UsersE2ETest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Container
    private static final MySQLContainer MYSQL_CONTAINER =
            new MySQLContainer("mysql:latest")
                    .withPassword("123456")
                    .withUsername("root")
                    .withDatabaseName("spaces_users_admin");

    @DynamicPropertySource
    public static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
        registry.add("mysql.port", () -> MYSQL_CONTAINER.getMappedPort(3306));
    }

    @Test
    void validate_containers_running_test() {
        assertTrue(MYSQL_CONTAINER.isRunning());
        assertEquals(0, userRepository.count());
    }

    @Test
    void givenAValidUser_whenCallsCreateAPI_thenReturnCreatedAndResponse() {
        var expectedUsername = "Pedro";
        var expectedPassword = "12345";
        var expectedEmail = "Pedro@gmail.com";
        var expectedBirthday = LocalDate.of(2002, 4, 4);
        var expectedGender = Gender.MAN;
        var expectedActive = true;
        var expectedTellphone = Tellphone.with("996403198", "55", "13");

        var actualUser = new CreateUserApiInput(
                expectedUsername,
                expectedPassword,
                expectedEmail,
                expectedBirthday,
                expectedGender,
                expectedTellphone.toString()
        );

        final var url = "/users";
        URI uri = URI.create(url);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<CreateUserApiInput> request = new HttpEntity<>(actualUser, headers);

        var response = this.testRestTemplate.postForEntity(uri, request, String.class);

        assertEquals(201, response.getStatusCode().value());

        var findInDb = userRepository.findById(1L);

        assertTrue(findInDb.isPresent());
        assertEquals(1L, findInDb.get().getId());
    }

    @Test
    void givenAnInValidUser_whenCallsCreateAPI_thenReturnError() {
        String expectedUsername = null;
        String expectedPassword = null;
        var expectedEmail = "Pedrogmail.com";
        var expectedBirthday = LocalDate.of(2023, 4, 4);
        var expectedGender = Gender.MAN;
        var expectedActive = true;
        var expectedTellphone = Tellphone.with("996403198", "55", "");

        var actualUser = new CreateUserApiInput(
                expectedUsername,
                expectedPassword,
                expectedEmail,
                expectedBirthday,
                expectedGender,
                expectedTellphone.toString()
        );

        final var url = "/users";
        URI uri = URI.create(url);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<CreateUserApiInput> request = new HttpEntity<>(actualUser, headers);

        var response = this.testRestTemplate.postForEntity(uri, request, String.class);
        assertEquals(400, response.getStatusCode().value());

        var findInDb = userRepository.count();

        assertEquals(0, findInDb);
    }
}
