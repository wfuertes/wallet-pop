package com.wfuertes;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.wfuertes.infra.messages.MessageService;
import com.wfuertes.infra.WalletModule;

public class WalletApp {

    public static void main(String[] args) {
        final Injector guice = Guice.createInjector(new WalletModule());
        final MessageService messageService = guice.getInstance(MessageService.class);

        messageService.initialize();
    }
}
