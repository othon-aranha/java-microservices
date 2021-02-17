package br.com.condominio.client;

import javax.annotation.Resource;
import javax.sql.DataSource;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {

	@Resource
	Environment env;
	 
	@Bean
	public DataSource dataSource() {
	    final DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    // dataSource.setDriverClassName(env.getProperty("driverClassName"spring.datasource.));
	    dataSource.setDriverClassName("org.sqlite.JDBC");//(env.getProperty("spring.datasource.driver-class-name"));
	    dataSource.setUrl("jdbc:sqlite:E:\\Desenvolvimento\\Repositorios\\Ionic\\condominio\\src\\app\\db\\admcond.db");//(env.getProperty("spring.datasource.jdbc-url"));
	    dataSource.setUsername("");//(env.getProperty("spring.datasource.user"));
	    dataSource.setPassword("");//(env.getProperty("spring.datasource.password"));
	    return dataSource;
	}

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}