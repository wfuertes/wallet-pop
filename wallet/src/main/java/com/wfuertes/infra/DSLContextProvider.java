package com.wfuertes.infra;

import com.google.inject.Provider;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderNameCase;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.SQLException;

public class DSLContextProvider implements Provider<DSLContext> {

    private static final Settings JOOQ_SETTINGS = new Settings().withRenderNameCase(RenderNameCase.LOWER)
                                                                .withRenderSchema(false);
    private final DataSource dataSource;

    @Inject
    public DSLContextProvider(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public DSLContext get() {
        try {
            return DSL.using(dataSource.getConnection(), SQLDialect.MYSQL, JOOQ_SETTINGS);
        } catch (SQLException err) {
            throw new RuntimeException("Fail to provide a DSLContext", err);
        }
    }
}
