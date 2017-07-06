package spittr.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;

@Configuration
public class DataConfig {

	@Bean
	public JndiObjectFactoryBean dataSource(){
		JndiObjectFactoryBean jndiObjectFB = new JndiObjectFactoryBean();
		jndiObjectFB.setJndiName("jdbc/spittrDS");
		jndiObjectFB.setResourceRef(true);
		jndiObjectFB.setProxyInterface(javax.sql.DataSource.class);
		return jndiObjectFB;
	}
	
	@Bean
	public JdbcOperations jdbcTemplate(DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}
}
