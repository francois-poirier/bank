package com.iobuiders.bank.domain.user.core;

import com.iobuiders.bank.domain.user.core.event.UserPublisher;
import com.iobuiders.bank.domain.user.application.command.AddUserCommand;
import com.iobuiders.bank.domain.user.core.model.EmailAddress;
import com.iobuiders.bank.domain.user.core.model.User;
import com.iobuiders.bank.domain.user.core.port.outgoing.UpdateUserPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class UserFacadeTest {

    public static final String FIRSTNAME = "FIRSTNAME";
    public static final String LASTNAME = "LASTNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String USERNAME = "USERNAME";
    public static final String EMAIL = "EMAIL@gmail.com";
    public static final UUID ID = UUID.randomUUID();
    private UpdateUserPort updateUserPort;
    private UserPublisher userPublisher;
    private UserFacade sut;


    @BeforeEach
    public void setUp() {
        this.updateUserPort = Mockito.mock(UpdateUserPort.class);
        this.userPublisher = Mockito.mock(UserPublisher.class);
        this.sut = new UserFacade(updateUserPort,userPublisher);
    }


    @Test
    void shouldBeHandleNewUser() {
        // given
        when(updateUserPort.save(any())).thenReturn(getUser());
        doNothing().when(userPublisher).publish(any());
        // when
        var response = this.sut.handle(getAddUserCommand());
        // then
        assertEquals(ID, response.getAsUuid());
    }

    private User getUser() {
        return User.builder()
                .id(ID)
                .firstName(FIRSTNAME)
                .lastName(LASTNAME)
                .password(PASSWORD)
                .username(USERNAME)
                .emailAddress(new EmailAddress(EMAIL))
                .build();
    }

    private AddUserCommand getAddUserCommand() {
        return AddUserCommand.builder()
                .firstName(FIRSTNAME)
                .lastName(LASTNAME)
                .password(PASSWORD)
                .username(USERNAME)
                .email(EMAIL)
                .build();
    }
}
