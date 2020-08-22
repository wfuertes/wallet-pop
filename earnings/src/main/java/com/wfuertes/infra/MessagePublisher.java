package com.wfuertes.infra;

import com.rabbitmq.client.*;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeoutException;

public class MessagePublisher {
    private static final boolean DURABLE = true;
    private static final String NO_ROUTING_KEY = "";
    private static final String EXCHANGE_NAME = "earnings";

    private final ConnectionFactory factory;

    @Inject
    public MessagePublisher(ConnectionFactory factory) {
        this.factory = factory;
    }

    public void publish(byte[] message) {
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT, DURABLE);

            final AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                    .timestamp(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime())
                    .build();

            channel.basicPublish(EXCHANGE_NAME, NO_ROUTING_KEY, properties, message);
        } catch (TimeoutException | IOException e) {
            throw new RuntimeException("Error when publishing message: " + new String(message), e);
        }
    }
}
