package com.wfuertes;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.wfuertes.earnings.EarningsConsumer;
import com.wfuertes.infra.WalletModule;

public class WalletApp {

    public static void main(String[] args) {
        final Injector guice = Guice.createInjector(new WalletModule());
        final EarningsConsumer earningsConsumer = guice.getInstance(EarningsConsumer.class);

        earningsConsumer.initialize();
    }
}
