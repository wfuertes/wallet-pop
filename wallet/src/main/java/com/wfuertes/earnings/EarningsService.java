package com.wfuertes.earnings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Optional;

public class EarningsService {

    private static final Logger LOG = LoggerFactory.getLogger(EarningsService.class);

    private final EarningsRepository repository;

    @Inject
    public EarningsService(EarningsRepository repository) {
        this.repository = repository;
    }

    public void handleEarningsUpdatedEvent(Earnings earnings) {
        Optional<Earnings> existing = repository.findById(earnings.jobId());

        if (existing.isPresent()) {

            if (earnings.version().compareTo(existing.get().version()) > 0) {
                repository.update(earnings);
                LOG.debug("UPDATING version: " + earnings);
            } else {
                LOG.debug("IGNORING event: " + earnings);
            }
        } else {
            repository.save(earnings);
            LOG.debug("INSERTING record: " + earnings);
        }
    }
}
