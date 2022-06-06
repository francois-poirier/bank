package com.iobuiders.bank.domain.wallet.core.event;

import com.iobuiders.bank.domain.wallet.core.model.TransactionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionPublisher {

    private final ApplicationEventPublisher publisher;

    public void publish(TransactionEvent event) {
        publisher.publishEvent(event);
    }

}
