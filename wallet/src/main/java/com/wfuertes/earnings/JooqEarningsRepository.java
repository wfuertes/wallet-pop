package com.wfuertes.earnings;

import com.wfuertes.sql.tables.records.EarningsRecord;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import javax.inject.Inject;
import java.util.Optional;

import static com.wfuertes.sql.Tables.EARNINGS;
import static java.time.ZoneOffset.UTC;

public class JooqEarningsRepository implements EarningsRepository {

    private final DSLContext context;

    @Inject
    public JooqEarningsRepository(DSLContext context) {
        this.context = context;
    }

    @Override
    public Optional<Earnings> findById(Long id) {

        return context.selectFrom(EARNINGS)
                .where(EARNINGS.JOB_ID.eq(id))
                .fetchOptional(record -> new Earnings(
                        record.getJobId(),
                        record.getFee(),
                        record.getTip(),
                        record.getBonus(),
                        record.getAdjustments(),
                        record.getSubsidies(),
                        Earnings.JobStatus.valueOf(record.getJobStatus()),
                        record.getVersion().toInstant(UTC)
                ));
    }

    @Override
    public void save(Earnings earnings) {
        context.transaction(config -> {
            DSL.using(config).insertInto(EARNINGS)
                    .set(new EarningsRecord(
                            earnings.jobId(),
                            earnings.fee(),
                            earnings.tip(),
                            earnings.bonus(),
                            earnings.adjustments(),
                            earnings.subsidies(),
                            earnings.jobStatus().name(),
                            earnings.version().atOffset(UTC).toLocalDateTime()
                    )).execute();
        });
    }

    @Override
    public void update(Earnings earnings) {
        context.transaction(config -> {
            DSL.using(config).update(EARNINGS)
                    .set(new EarningsRecord(
                            earnings.jobId(),
                            earnings.fee(),
                            earnings.tip(),
                            earnings.bonus(),
                            earnings.adjustments(),
                            earnings.subsidies(),
                            earnings.jobStatus().name(),
                            earnings.version().atOffset(UTC).toLocalDateTime()
                    )).where(EARNINGS.JOB_ID.eq(earnings.jobId()))
                    .execute();
        });
    }
}
