package com.iobuiders.bank.domain.user.application;

import com.iobuiders.bank.domain.user.core.port.incoming.AddNewUser;
import com.iobuiders.bank.domain.user.application.command.AddUserCommand;
import com.iobuiders.bank.domain.user.core.model.UserIdentifier;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Tag(name = "users", description = "the users API")
@RequestMapping("/api/v1/users")
@Validated
public class UserController {

    private final AddNewUser addNewUser;

    @Operation(summary = "Add new user", description = "", tags = { "users" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create new user",
                    content = @Content(schema = @Schema(implementation = UserIdentifier.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PostMapping
    public ResponseEntity<UserIdentifier> addNewUser(@Parameter(description="Add new user command. Cannot null or empty.",
            required=true, schema=@Schema(implementation = AddUserCommand.class)) @Valid @RequestBody AddUserCommand addUserCommand){
        return new ResponseEntity<>( addNewUser.handle(addUserCommand), HttpStatus.CREATED);
    }
}