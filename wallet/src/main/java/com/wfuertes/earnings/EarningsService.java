package com.wfuertes.earnings;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class EarningsService {

    private static final Map<Long, Earnings> repository = new TreeMap<>();

    public void handleEarningsUpdatedEvent(Earnings earnings) {
        Optional<Earnings> existing = Optional.ofNullable(repository.get(earnings.jobId()));

        if (existing.isPresent()) {
            if (earnings.version().compareTo(existing.get().version()) > 0) {
                repository.put(earnings.jobId(), earnings);
                System.out.println("UPDATING version: " + earnings);
            } else {
                System.out.println("IGNORING event: " + earnings);
            }
        } else {
            repository.put(earnings.jobId(), earnings);
            System.out.println("INSERTING record: " + earnings);
        }
    }
}
