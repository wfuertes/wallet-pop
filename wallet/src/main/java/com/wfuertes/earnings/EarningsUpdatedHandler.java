package com.wfuertes.earnings;

import com.wfuertes.events.EventHandler;
import com.wfuertes.infra.JsonMapper;

import javax.inject.Inject;
import java.time.Instant;

public class EarningsUpdatedHandler implements EventHandler {

    private final JsonMapper mapper;
    private final EarningsService earningsService;

    @Inject
    public EarningsUpdatedHandler(JsonMapper mapper, EarningsService earningsService) {
        this.mapper = mapper;
        this.earningsService = earningsService;
    }

    @Override
    public void handle(String eventBody, long createdAt) {
        final EarningsUpdatedEvent event = mapper.fromJson(eventBody, EarningsUpdatedEvent.class);
        earningsService.handleEarningsUpdatedEvent(event.toDomain(Instant.ofEpochMilli(createdAt)));
    }
}
