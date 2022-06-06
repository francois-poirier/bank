package com.iobuiders.bank.domain.user.core;

import com.iobuiders.bank.domain.user.core.event.UserPublisher;
import com.iobuiders.bank.domain.user.core.model.*;
import com.iobuiders.bank.domain.user.core.port.incoming.AddNewUser;
import com.iobuiders.bank.domain.user.core.port.outgoing.UpdateUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class UserFacade implements AddNewUser {

    private final UpdateUserPort updateUserPort;
    private final UserPublisher userPublisher;
    @Override
    public UserIdentifier handle(AddUserCommand addUserCommand) {
        User user = new User(
                new EmailAddress(addUserCommand.getEmail()),
                addUserCommand.getFirstName(),
                addUserCommand.getLastName(),
                addUserCommand.getUsername(),
                addUserCommand.getPassword()
        );
        User savedUser = updateUserPort.save(user);
        userPublisher.publish(new UserEvent(this,savedUser));
        return new UserIdentifier(savedUser.getId());
    }
}
