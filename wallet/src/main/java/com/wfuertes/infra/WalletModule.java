package com.wfuertes.infra;

import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.rabbitmq.client.ConnectionFactory;
import com.wfuertes.earnings.EarningsConsumer;
import com.wfuertes.earnings.EarningsUpdateHandler;

public class WalletModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ConnectionFactory.class).toProvider(() -> {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setVirtualHost("/");
            factory.setHost("localhost");
            factory.setPort(5672);
            return factory;
        });

        bind(GsonBuilder.class).toInstance(new GsonBuilder());
        bind(JsonMapper.class);
        bind(MessageConsumer.class);
        bind(EarningsUpdateHandler.class);
        bind(EarningsConsumer.class);
    }
}
