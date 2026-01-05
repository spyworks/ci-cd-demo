package com.example.cicddemo.config;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class EclipseLinkJpaConfig extends JpaBaseConfiguration {

    public EclipseLinkJpaConfig(
            DataSource dataSource,
            JpaProperties properties,
            ObjectProvider<JtaTransactionManager> jtaTransactionManager
    ) {
        super(dataSource, properties, jtaTransactionManager);
    }

    @Override
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        EclipseLinkJpaVendorAdapter adapter = new EclipseLinkJpaVendorAdapter();
        adapter.setShowSql(false);
        adapter.setGenerateDdl(false);
        return adapter;
    }

    @Override
    protected Map<String, Object> getVendorProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put(PersistenceUnitProperties.WEAVING, "false");
        props.put(PersistenceUnitProperties.LOGGING_LEVEL, "INFO");
        props.put(PersistenceUnitProperties.DDL_GENERATION, "none");
        return props;
    }
}