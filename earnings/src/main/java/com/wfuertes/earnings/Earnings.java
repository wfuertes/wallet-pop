package com.wfuertes.earnings;

public class Earnings {
    private final long jobId;
    private final int fee;
    private final int tip;
    private final int bonus;
    private final int adjustments;
    private final int subsidies;
    private final JobStatus jobStatus;
    private final String employeeId;

    public Earnings(long jobId, int fee, int tip, int bonus, int adjustments, int subsidies, JobStatus jobStatus, String employeeId) {
        this.jobId = jobId;
        this.fee = fee;
        this.tip = tip;
        this.bonus = bonus;
        this.adjustments = adjustments;
        this.subsidies = subsidies;
        this.jobStatus = jobStatus;
        this.employeeId = employeeId;
    }

    public enum JobStatus {
        COMPLETED, CANCELLED, REJECTED
    }
}
