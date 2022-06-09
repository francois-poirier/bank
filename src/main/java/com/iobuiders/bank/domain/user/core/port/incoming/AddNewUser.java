package com.iobuiders.bank.domain.user.core.port.incoming;

import com.iobuiders.bank.domain.user.application.command.AddUserCommand;
import com.iobuiders.bank.domain.user.core.model.UserIdentifier;

public interface AddNewUser {

    UserIdentifier handle(AddUserCommand addUserCommand);
}
