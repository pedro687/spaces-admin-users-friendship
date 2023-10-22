package com.spaces.user.svc.infrastructure.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaces.user.svc.application.user.CreateUserUseCase;
import com.spaces.user.svc.application.user.UserCreatedOutput;
import com.spaces.user.svc.domain.users.Gender;
import com.spaces.user.svc.domain.users.Tellphone;
import com.spaces.user.svc.domain.users.User;
import com.spaces.user.svc.infrastructure.ControllerTest;
import com.spaces.user.svc.infrastructure.api.UserAPI;
import com.spaces.user.svc.infrastructure.users.models.CreateUserApiInput;
import io.vavr.API;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Objects;


@ControllerTest(controllers = UserAPI.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateUserUseCase createUserUseCase;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void givenAValidcommand_whenCallsCreateUser_thenReturnUser() throws Exception {
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

        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(this.objectMapper.writeValueAsString(actualUser));

        Mockito.when(createUserUseCase.execute(Mockito.any()))
                .thenReturn(API.Right(
                        UserCreatedOutput.from("d9f4c041-35f0-49c2-8c36-eb139f4155e0")
                ));

        this.mockMvc.perform(req)
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(
                        MockMvcResultMatchers.status().isCreated(),
                        MockMvcResultMatchers.header().string("Location", "/users/d9f4c041-35f0-49c2-8c36-eb139f4155e0")
                );

        Mockito.verify(createUserUseCase, Mockito.times(1))
                .execute(Mockito.argThat(cmd ->
                    Objects.equals(expectedUsername, cmd.username())
                    && Objects.equals(expectedPassword,  cmd.password())
                            && Objects.equals(expectedEmail, cmd.email())
                            && Objects.equals(expectedBirthday, cmd.birthdayDate())
                            && Objects.equals(expectedGender, cmd.gender())
                            && Objects.equals(expectedTellphone.toString(), cmd.tellphone().toString())
                ));
    }

}