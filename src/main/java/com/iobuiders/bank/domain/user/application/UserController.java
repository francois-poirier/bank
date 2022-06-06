package com.iobuiders.bank.domain.user.application;

import com.iobuiders.bank.domain.user.core.port.incoming.AddNewUser;
import com.iobuiders.bank.domain.user.core.model.AddUserCommand;
import com.iobuiders.bank.domain.user.core.model.UserIdentifier;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "users", description = "the users API")
@RequestMapping("/api/v1/users")
public class UserController {

    private final AddNewUser addNewUser;

    @PostMapping
    public ResponseEntity<UserIdentifier> addNewUser(@RequestBody AddUserCommand addUserCommand){
        return new ResponseEntity<>( addNewUser.handle(addUserCommand), HttpStatus.CREATED);
    }
}