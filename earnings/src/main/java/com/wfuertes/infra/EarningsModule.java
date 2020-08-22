package com.wfuertes.infra;

import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.rabbitmq.client.ConnectionFactory;
import com.wfuertes.earnings.EarningsService;

public class EarningsModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ConnectionFactory.class).toInstance(connectionFactory());
        bind(GsonBuilder.class).toInstance(new GsonBuilder());
        bind(JsonMapper.class);
        bind(MessagePublisher.class);
        bind(EarningsService.class);
    }

    private ConnectionFactory connectionFactory() {
        final ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        factory.setHost("localhost");
        factory.setPort(5672);
        return factory;
    }
}
