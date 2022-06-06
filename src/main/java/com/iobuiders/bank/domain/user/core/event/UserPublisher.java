package com.iobuiders.bank.domain.user.core.event;

import com.iobuiders.bank.domain.user.core.model.UserEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPublisher {

    private final ApplicationEventPublisher publisher;

    public void publish(UserEvent event) {
        publisher.publishEvent(event);
    }

}
