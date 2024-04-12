package gudmundsson.com.invoice.util.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.zaxxer.hikari.HikariDataSource;

import gudmundsson.com.invoice.util.AElog;


@Configuration
@MapperScan(basePackages = "gudmundsson.com.invoice.dao", sqlSessionFactoryRef = "oneSqlSessionFactory")
public class MyBatisConfig {

	private static final Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);

    @Value("${mobarmy.properties.source}")
    private String source;

    @Value("${mybatis.mapper-locations}")
    private String mybatis_mapper_locations;

    @Primary
    @Bean(name = "oneDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.one.hikari")
    DataSource primaryDataSource() {
        AElog.infoX(logger, "El proceso primaryDataSource() fue invocado. Properties: " + source);
        // for HikariDataSource use:
        // Option 0: Spring Boot by default uses HikariDataSource
        // Option 1: spring.datasource.xyz.type=com.zaxxer.hikari.HikariDataSource
        // Option 2: DataSourceBuilder.create().type(HikariDataSource.class).build();
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "oneSqlSessionFactory")
    SqlSessionFactory sqlSessionFactory(@Qualifier("oneDataSource") DataSource dataSource) throws Exception {
        AElog.infoX(logger, "El proceso sqlSessionFactory() fue invocado. Properties: " + source);
        HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
        AElog.infoX(logger, "getJdbcUrl: " + hikariDataSource.getJdbcUrl());
        AElog.infoX(logger, "getUsername: *"); // + hikariDataSource.getUsername());
        AElog.infoX(logger, "getPassword: *"); // + hikariDataSource.getPassword());
        AElog.infoX(logger, "getDriverClassName: " + hikariDataSource.getDriverClassName());
        AElog.infoX(logger, "getDataSourceClassName: " + hikariDataSource.getDataSourceClassName());
        AElog.infoX(logger, "getConnectionTimeout: " + hikariDataSource.getConnectionTimeout());
        AElog.infoX(logger, "getMinimumIdle: " + hikariDataSource.getMinimumIdle());
        AElog.infoX(logger, "getMaximumPoolSize: " + hikariDataSource.getMaximumPoolSize());
        AElog.infoX(logger, "getIdleTimeout: " + hikariDataSource.getIdleTimeout());
        AElog.infoX(logger, "getMaxLifetime: " + hikariDataSource.getMaxLifetime());
        AElog.infoX(logger, "isAutoCommit: " + hikariDataSource.isAutoCommit());

        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean
                .setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mybatis_mapper_locations));

        return sessionFactoryBean.getObject();
    }
}
