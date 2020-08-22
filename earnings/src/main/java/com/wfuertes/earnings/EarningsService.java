package com.wfuertes.earnings;

import com.wfuertes.infra.JsonMapper;
import com.wfuertes.infra.MessagePublisher;

import javax.inject.Inject;

public class EarningsService {
    private final JsonMapper mapper;
    private final MessagePublisher publisher;

    @Inject
    public EarningsService(JsonMapper mapper, MessagePublisher publisher) {
        this.mapper = mapper;
        this.publisher = publisher;
    }

    public void publishEarningsUpdatedEvent(Earnings earnings) {
        publisher.publish("EARNINGS_UPDATED", mapper.toJson(earnings));
    }
}
