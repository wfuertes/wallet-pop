package com.wfuertes.earnings;

import com.wfuertes.infra.JsonMapper;

import javax.inject.Inject;
import java.time.Instant;

public class EarningsUpdateHandler implements EventHandler {

    private final JsonMapper mapper;
    private final EarningsService earningsService;

    @Inject
    public EarningsUpdateHandler(JsonMapper mapper, EarningsService earningsService) {
        this.mapper = mapper;
        this.earningsService = earningsService;
    }

    @Override
    public void handle(String eventBody, long createdAt) {
        final EarningsUpdateEvent event = mapper.fromJson(eventBody, EarningsUpdateEvent.class);
        earningsService.handleEarningsUpdatedEvent(event.toDomain(Instant.ofEpochMilli(createdAt)));
    }
}
