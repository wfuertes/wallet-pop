package com.wfuertes.infra.messages;

import com.wfuertes.earnings.EarningsUpdatedHandler;

import javax.inject.Inject;

public class MessageService {

    private final MessageSubscriber subscriber;
    private final EarningsUpdatedHandler earningsUpdatedHandler;

    @Inject
    public MessageService(MessageSubscriber subscriber, EarningsUpdatedHandler earningsUpdatedHandler) {
        this.subscriber = subscriber;
        this.earningsUpdatedHandler = earningsUpdatedHandler;
    }

    public void initialize() {
        subscriber.subscribe("EARNINGS_UPDATED", earningsUpdatedHandler);
    }
}
