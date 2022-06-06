package com.iobuiders.bank.domain.user.infrastructure;

import com.iobuiders.bank.domain.user.core.model.EmailAddress;
import com.iobuiders.bank.domain.user.core.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    Optional<User> findByEmailAddress(EmailAddress emailAddress);
}