package com.wfuertes.earnings;

import com.wfuertes.infra.JsonMapper;
import com.wfuertes.infra.MessagePublisher;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;

public class EarningsService {
    private final JsonMapper mapper;
    private final MessagePublisher publisher;

    @Inject
    public EarningsService(JsonMapper mapper, MessagePublisher publisher) {
        this.mapper = mapper;
        this.publisher = publisher;
    }

    public void publishEarnings(Earnings earnings) {
        publisher.publish(mapper.toJson(earnings).getBytes(StandardCharsets.UTF_8));
    }
}
