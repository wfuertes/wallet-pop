package com.wfuertes.infra;

import com.wfuertes.earnings.EarningsUpdateHandler;

import javax.inject.Inject;

public class MessageService {

    private final MessageSubscriber subscriber;
    private final EarningsUpdateHandler earningsUpdateHandler;

    @Inject
    public MessageService(MessageSubscriber subscriber, EarningsUpdateHandler earningsUpdateHandler) {
        this.subscriber = subscriber;
        this.earningsUpdateHandler = earningsUpdateHandler;
    }

    public void initialize() {
        subscriber.subscribe("EARNINGS_UPDATED", earningsUpdateHandler);
    }
}
