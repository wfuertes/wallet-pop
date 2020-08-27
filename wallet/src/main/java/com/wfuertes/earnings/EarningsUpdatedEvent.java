package com.wfuertes.earnings;

import java.time.Instant;

public class EarningsUpdatedEvent {

    private final long jobId;
    private final int fee;
    private final int tip;
    private final int bonus;
    private final int adjustments;
    private final int subsidies;
    private final Earnings.JobStatus jobStatus;

    public EarningsUpdatedEvent(long jobId,
                                int fee,
                                int tip,
                                int bonus,
                                int adjustments,
                                int subsidies,
                                Earnings.JobStatus jobStatus) {
        this.jobId = jobId;
        this.fee = fee;
        this.tip = tip;
        this.bonus = bonus;
        this.adjustments = adjustments;
        this.subsidies = subsidies;
        this.jobStatus = jobStatus;
    }

    public Earnings toDomain(Instant version) {
        return new Earnings(jobId, fee, tip, bonus, adjustments, subsidies, jobStatus, version);
    }
}
