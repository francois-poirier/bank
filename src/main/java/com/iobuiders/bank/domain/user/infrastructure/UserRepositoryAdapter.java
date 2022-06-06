package com.iobuiders.bank.domain.user.infrastructure;

import com.iobuiders.bank.domain.user.core.model.EmailAddress;
import com.iobuiders.bank.domain.user.core.port.outgoing.UpdateUserPort;
import com.iobuiders.bank.domain.user.core.model.User;
import com.iobuiders.bank.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UpdateUserPort {
    private final UserRepository userRepository;
    @Override
    public User save(User user) {

        EmailAddress emailAddress = user.getEmailAddress();
        Optional<User> optUser = userRepository.findByEmailAddress(emailAddress);
        if (optUser.isPresent()) {
            throw new BusinessException(String.format("The user with email %s exist", emailAddress.getValue()));
        }
        return userRepository.save(user);
    }
}
