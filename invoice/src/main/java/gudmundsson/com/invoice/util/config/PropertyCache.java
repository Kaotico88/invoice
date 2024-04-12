package gudmundsson.com.invoice.util.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import gudmundsson.com.invoice.util.AElog;

@Component
@Configuration
public class PropertyCache {

	private static final Logger logger = LoggerFactory.getLogger(PropertyCache.class);

    public TimeoutSetup timeoutSetup;

    @Value("${server.port}")
    private int server_port;

    @Value("${mobarmy.properties.source}")
    private String mobarmy_properties_source;

    @Value("${spring.datasource.one.hikari.jdbc-url}")
    private String spring_datasource_one_hikari_jdbc_url;

    @Value("${spring.datasource.one.hikari.username}")
    private String spring_datasource_one_hikari_username;

    @Value("${spring.datasource.one.hikari.password}")
    private String spring_datasource_one_hikari_password;

    @Value("${spring.datasource.one.hikari.driver-class-name}")
    private String spring_datasource_one_hikari_driver_class_name;

    @Value("${spring.datasource.one.hikari.connection-timeout}")
    private int spring_datasource_one_hikari_connection_timeout;

    @Value("${spring.datasource.one.hikari.minimum-idle}")
    private int spring_datasource_one_hikari_minimum_idle;

    @Value("${spring.datasource.one.hikari.maximum-pool-size}")
    private int spring_datasource_one_hikari_maximum_pool_size;

    @Value("${spring.datasource.one.hikari.idle-timeout}")
    private int spring_datasource_one_hikari_idle_timeout;

    @Value("${spring.datasource.one.hikari.max-lifetime}")
    private int spring_datasource_one_hikari_max_lifetime;

    @Value("${spring.datasource.one.hikari.auto-commit}")
    private boolean spring_datasource_one_hikari_auto_commit;

    @Bean
    void printProperties() {
        timeoutSetup = new TimeoutSetup(30, 30, 30);
        log("------------------------------------------------------------------------------------------");
        log("Propiedades con los que trabajara el modulo");
        log("------------------------------------------------------------------------------------------");
        log("server.port: " + server_port);
        log("mobarmy.properties.source: " + mobarmy_properties_source);
        log("spring.datasource.one.hikari.jdbc-url: " + spring_datasource_one_hikari_jdbc_url);
        log("spring.datasource.one.hikari.username: *"); // + spring_datasource_one_hikari_username);
        log("spring.datasource.one.hikari.password: *"); // + spring_datasource_one_hikari_password);
        log("spring.datasource.one.hikari.driver-class-name: " + spring_datasource_one_hikari_driver_class_name);
        log("spring.datasource.one.hikari.connection-timeout: " + spring_datasource_one_hikari_connection_timeout);
        log("spring.datasource.one.hikari.minimum-idle: " + spring_datasource_one_hikari_minimum_idle);
        log("spring.datasource.one.hikari.maximum-pool-size: " + spring_datasource_one_hikari_maximum_pool_size);
        log("spring.datasource.one.hikari.idle-timeout: " + spring_datasource_one_hikari_idle_timeout);
        log("spring.datasource.one.hikari.max-lifetime: " + spring_datasource_one_hikari_max_lifetime);
        log("spring.datasource.one.hikari.auto-commit: " + spring_datasource_one_hikari_auto_commit);
        log("------------------------------------------------------------------------------------------");
    }

    private void log(String textLog) {
        AElog.infoX(logger, textLog);
    }
}
