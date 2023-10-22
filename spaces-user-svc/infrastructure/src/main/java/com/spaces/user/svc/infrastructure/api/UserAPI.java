package com.spaces.user.svc.infrastructure.api;

import com.spaces.user.svc.domain.validation.handler.Notification;
import com.spaces.user.svc.infrastructure.exceptions.ErrorResponseDto;
import com.spaces.user.svc.infrastructure.users.models.CreateUserApiInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping(value = "/users")
@Tag(
        name = "CRUD REST API for Spaces Users",
        description = "CRUD REST API in Spaces Users to POST, PUT, GET AND PATCH"
)
public interface UserAPI {


    @Operation(
            summary = "Create Account Rest API",
            description = "Create a new user in Spaces"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "UnprocessableEntity",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> createUser(@RequestBody CreateUserApiInput createUserApiInput);
}
