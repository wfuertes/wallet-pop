package com.wfuertes.infra;

import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.rabbitmq.client.ConnectionFactory;
import com.wfuertes.earnings.EarningsRepository;
import com.wfuertes.earnings.EarningsUpdatedHandler;
import com.wfuertes.earnings.JooqEarningsRepository;
import com.wfuertes.infra.messages.MessageService;
import com.wfuertes.infra.messages.MessageSubscriber;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.jooq.DSLContext;

import javax.sql.DataSource;

public class WalletModule extends AbstractModule {

    @Override
    protected void configure() {
        final Flyway flyway = flyway();
        flyway.migrate();

        bind(ConnectionFactory.class).toInstance(rabbitConnectionFactory());
        bind(GsonBuilder.class).toInstance(new GsonBuilder());
        bind(DataSource.class).toInstance(flyway.getConfiguration().getDataSource());
        bind(DSLContext.class).toProvider(DSLContextProvider.class);
        bind(JsonMapper.class);
        bind(MessageSubscriber.class);
        bind(EarningsUpdatedHandler.class);
        bind(MessageService.class);
        bind(EarningsRepository.class).to(JooqEarningsRepository.class);
    }

    private ConnectionFactory rabbitConnectionFactory() {
        final ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        factory.setHost("localhost");
        factory.setPort(5672);
        return factory;
    }

    private DataSource dataSource() {
        final HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/wallet?useSSL=false&serverTimezone=UTC");
        config.setUsername("root");
        config.setPassword("root");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }

    private Flyway flyway() {
        return Flyway.configure().table("schema_history").dataSource(dataSource()).load();
    }
}
