package com.wfuertes.earnings;

import com.wfuertes.infra.MessageConsumer;

import javax.inject.Inject;

public class EarningsConsumer {

    private final MessageConsumer messageConsumer;
    private final EarningsUpdateHandler earningsUpdateHandler;

    @Inject
    public EarningsConsumer(MessageConsumer messageConsumer, EarningsUpdateHandler earningsUpdateHandler) {
        this.messageConsumer = messageConsumer;
        this.earningsUpdateHandler = earningsUpdateHandler;
    }

    public void initialize() {
        messageConsumer.consume(earningsUpdateHandler);
    }
}
