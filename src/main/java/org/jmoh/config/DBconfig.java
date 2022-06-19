package org.jmoh.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class DBconfig {

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/loginsys?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("loginsys");
        dataSource.setPassword("1234");
        return dataSource;
    }
}
