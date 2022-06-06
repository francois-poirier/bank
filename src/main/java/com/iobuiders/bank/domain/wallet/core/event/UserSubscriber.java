package com.iobuiders.bank.domain.wallet.core.event;

import com.iobuiders.bank.domain.user.core.model.UserEvent;
import com.iobuiders.bank.domain.wallet.core.port.incoming.AddNewWallet;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserSubscriber implements ApplicationListener<UserEvent> {

    private AddNewWallet addNewWallet;

    @Override
    public void onApplicationEvent(UserEvent event) {
        addNewWallet.create(event.getUser());
    }
}
