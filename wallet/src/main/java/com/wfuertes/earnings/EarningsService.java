package com.wfuertes.earnings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class EarningsService {

    private static final Logger LOG = LoggerFactory.getLogger(EarningsService.class);

    private static final Map<Long, Earnings> repository = new TreeMap<>();

    public void handleEarningsUpdatedEvent(Earnings earnings) {
        Optional<Earnings> existing = Optional.ofNullable(repository.get(earnings.jobId()));

        if (existing.isPresent()) {
            if (earnings.version().compareTo(existing.get().version()) > 0) {
                repository.put(earnings.jobId(), earnings);
                LOG.debug("UPDATING version: " + earnings);
            } else {
                LOG.debug("IGNORING event: " + earnings);
            }
        } else {
            repository.put(earnings.jobId(), earnings);
            LOG.debug("INSERTING record: " + earnings);
        }
    }
}
