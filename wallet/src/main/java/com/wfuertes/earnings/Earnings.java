package com.wfuertes.earnings;

import java.time.Instant;

public class Earnings {

    private final long jobId;
    private final int fee;
    private final int tip;
    private final int bonus;
    private final int adjustments;
    private final int subsidies;
    private final JobStatus jobStatus;
    private final String employeeId;
    private final Instant version;

    public Earnings(long jobId, int fee, int tip, int bonus, int adjustments, int subsidies, JobStatus jobStatus, String employeeId, Instant version) {
        this.jobId = jobId;
        this.fee = fee;
        this.tip = tip;
        this.bonus = bonus;
        this.adjustments = adjustments;
        this.subsidies = subsidies;
        this.jobStatus = jobStatus;
        this.employeeId = employeeId;
        this.version = version;
    }

    public long jobId() {
        return jobId;
    }

    public int fee() {
        return fee;
    }

    public int tip() {
        return tip;
    }

    public int bonus() {
        return bonus;
    }

    public int adjustments() {
        return adjustments;
    }

    public int subsidies() {
        return subsidies;
    }

    public JobStatus jobStatus() {
        return jobStatus;
    }

    public String employeeId() {
        return employeeId;
    }

    public Instant version() {
        return version;
    }

    @Override
    public String toString() {
        return "Earnings{" +
                "jobId=" + jobId +
                ", fee=" + fee +
                ", tip=" + tip +
                ", bonus=" + bonus +
                ", adjustments=" + adjustments +
                ", subsidies=" + subsidies +
                ", jobStatus=" + jobStatus +
                ", employeeId='" + employeeId + '\'' +
                ", version=" + version +
                '}';
    }

    public enum JobStatus {
        COMPLETED, CANCELLED, REJECTED
    }
}
