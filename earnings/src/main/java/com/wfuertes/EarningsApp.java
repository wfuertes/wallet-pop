package com.wfuertes;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.wfuertes.earnings.Earnings;
import com.wfuertes.earnings.EarningsService;
import com.wfuertes.infra.EarningsModule;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class EarningsApp {

    public static void main(String[] args) {
        final Injector guice = Guice.createInjector(new EarningsModule());
        final EarningsService earningsService = guice.getInstance(EarningsService.class);
        final Supplier<Boolean> stopEarnings = () -> Optional.ofNullable(System.getenv("STOP_EARNINGS"))
                .map(Boolean::valueOf)
                .orElse(false);

        while (!stopEarnings.get()) {
            long baseId = Instant.now().toEpochMilli();

            Stream.generate(() -> new Earnings(
                    baseId + random(1, 1000),
                    random(100, 500),
                    random(0, 300),
                    random(0, 200),
                    random(0, 200),
                    random(50, 100),
                    fakeJobStatus()
            )).limit(1000).forEach(earningsService::publishEarningsUpdatedEvent);
        }
    }

    private static int random(int minimum, int maximum) {
        return new Random().nextInt(maximum) + minimum;
    }

    private static Earnings.JobStatus fakeJobStatus() {
        final int random = random(1, 100);
        if (random > 95) {
            return Earnings.JobStatus.REJECTED;
        }
        if (random > 90) {
            return Earnings.JobStatus.CANCELLED;
        }
        return Earnings.JobStatus.COMPLETED;
    }
}
