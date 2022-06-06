package com.iobuiders.bank.domain.wallet.core.event;

import com.iobuiders.bank.domain.wallet.core.model.TransactionEvent;
import com.iobuiders.bank.domain.wallet.core.port.incoming.AddNewTransaction;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TransactionSubscriber implements ApplicationListener<TransactionEvent> {

    private AddNewTransaction addNewTransaction;

    @Override
    public void onApplicationEvent(TransactionEvent event) {
        addNewTransaction.create(event.getTransaction());
    }
}
