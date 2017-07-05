package spittr.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.ldapAuthentication()
				.userSearchBase("ou=people")
				.userSearchFilter("(uid={0}")
				.groupSearchBase("ou=groups")
				.groupSearchFilter("member={0}")
				.contextSource()
					.root("dc=habuma, dc=com");
	}
	
	
	//Configuración autentificación LDAP Remoto
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.ldapAuthentication()
//				.userSearchBase("ou=people")
//				.userSearchFilter("(uid={0}")
//				.groupSearchBase("ou=groups")
//				.groupSearchFilter("member={0}")
//				.contextSource()
//					.url("ldap://habuma.com:389/dc=habuma,dc=com");
//	}
	
	//Configuració autentificación por BBDD
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication()
//			.dataSource(dataSource)
//			.usersByUsernameQuery("select username, password, true"+
//								"from Spitter where username=?")
//			.authoritiesByUsernameQuery("select username, 'ROLE_USER' from Spitter where username=?")
//			.passwordEncoder(new StandardPasswordEncoder("53cr3t"));
//	}
	
	//Repositorio de usuarios en memoria
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("user").password("password").roles("USER").and()
//			.withUser("admin").password("password").roles("USER", "ADMIN");
//	}
}
