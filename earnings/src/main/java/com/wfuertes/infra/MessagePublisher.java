package com.wfuertes.infra;

import com.rabbitmq.client.*;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class MessagePublisher {
    private static final boolean DURABLE = true;
    private static final String EXCHANGE_NAME = "earnings";

    private final ConnectionFactory factory;

    @Inject
    public MessagePublisher(ConnectionFactory factory) {
        this.factory = factory;
    }

    public void publish(String eventName, String messageBody) {
        try (final Connection connection = factory.newConnection();
             final Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT, DURABLE);

            final AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                    .timestamp(Date.from(Instant.now()))
                    .build();

            channel.basicPublish(EXCHANGE_NAME, eventName, properties, messageBody.getBytes(StandardCharsets.UTF_8));
        } catch (TimeoutException | IOException e) {
            throw new RuntimeException("Error when publishing message: " + messageBody, e);
        }
    }
}
