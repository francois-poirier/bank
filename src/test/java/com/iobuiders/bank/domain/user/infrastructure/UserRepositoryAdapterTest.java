package com.iobuiders.bank.domain.user.infrastructure;

import com.iobuiders.bank.domain.user.core.model.EmailAddress;
import com.iobuiders.bank.domain.user.core.model.User;
import com.iobuiders.bank.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserRepositoryAdapterTest {

    public static final String FIRSTNAME = "FIRSTNAME";
    public static final String LASTNAME = "LASTNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String USERNAME = "USERNAME";
    public static final String EMAIL = "EMAIL@gmail.com";
    public static final UUID ID = UUID.randomUUID();
    private UserRepository userRepository;
    private UserRepositoryAdapter sut;

    @BeforeEach
    public void setUp() {
        this.userRepository = Mockito.mock(UserRepository.class);
        this.sut = new UserRepositoryAdapter(userRepository);
    }

    @Test
    void shouldBeNotSaveUser() {
        // given
        EmailAddress emailAddress = getEmailAddress();
        User user = getUser();
        when(userRepository.findByEmailAddress(emailAddress)).thenReturn(Optional.of(user));
        // when
        assertThrows(BusinessException.class, () -> {
        this.sut.save(user);
        });
        // then
        verify(userRepository, times(1)).findByEmailAddress(emailAddress);
    }

    @Test
    void shouldBeSaveUser() {
        // given
        EmailAddress emailAddress = getEmailAddress();
        User user = getUser();
        when(userRepository.findByEmailAddress(emailAddress)).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);
        // when
        this.sut.save(user);
        // then
        verify(userRepository, times(1)).findByEmailAddress(emailAddress);
        verify(userRepository, times(1)).save(user);
    }

    private User getUser() {
        return User.builder()
                .id(ID)
                .firstName(FIRSTNAME)
                .lastName(LASTNAME)
                .password(PASSWORD)
                .username(USERNAME)
                .emailAddress(getEmailAddress())
                .build();
    }

    private EmailAddress getEmailAddress() {
        return new EmailAddress(EMAIL);
    }
}
