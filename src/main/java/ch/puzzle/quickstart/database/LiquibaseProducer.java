package ch.puzzle.quickstart.database;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

import liquibase.integration.cdi.CDILiquibaseConfig;
import liquibase.integration.cdi.annotations.LiquibaseType;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

public class LiquibaseProducer {
	
	@Resource(lookup = "java:jboss/datasources/test")
    private DataSource dataSource;

    @Produces @LiquibaseType
    public CDILiquibaseConfig createConfig() {
        CDILiquibaseConfig config = new CDILiquibaseConfig();
        //TODO: read from property file
        config.setChangeLog("liquibase/db.changelog.xml");
        return config;
    }

    @Produces @LiquibaseType
    public DataSource createDataSource() throws SQLException {
        return dataSource;
    }

    @Produces @LiquibaseType
    public ResourceAccessor create() {
        return new ClassLoaderResourceAccessor(getClass().getClassLoader());
    }

}
