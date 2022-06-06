package com.iobuiders.bank.domain.user.core.port.outgoing;

import com.iobuiders.bank.domain.user.core.model.User;

import java.util.UUID;

public interface UpdateUserPort {
    User save(User user);
}
