package com.iobuiders.bank.domain.user.core.model;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class UserEvent extends ApplicationEvent {

    @Getter
    private User user;

    public UserEvent(Object src, User newUser) {
        super(src);
        this.user = newUser;
    }

    @Override
    public String toString() {
        return this.user.toString();
    }

}