package com.wfuertes.infra;

import com.rabbitmq.client.*;
import com.wfuertes.earnings.EventHandler;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeoutException;

public class MessageConsumer {
    private static final String NO_ROUTING_KEY = "";
    private static final String EXCHANGE_NAME = "earnings";

    private final ConnectionFactory factory;

    @Inject
    public MessageConsumer(ConnectionFactory factory) {
        this.factory = factory;
    }

    public void consume(EventHandler handler) {
        try {
            final Channel channel = factory.newConnection().createChannel();
            final String queue = channel.queueDeclare("wallet.EARNINGS_UPDATED", true, false, false, Collections.emptyMap()).getQueue();
            channel.queueBind(queue, EXCHANGE_NAME, NO_ROUTING_KEY);

            channel.basicConsume(queue, false, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    handler.handle(new String(body), properties.getTimestamp().getTime());
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            });
        } catch (TimeoutException | IOException e) {
            throw new RuntimeException("Error when consuming message from: " + EXCHANGE_NAME, e);
        }
    }
}
