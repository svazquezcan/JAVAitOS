package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/**
 * Configuración de LDAP
 * @author aimry
 *
 */
@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/**
	 * Se añaden las reglas de autenticación
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().fullyAuthenticated()
				.and().csrf().disable()
			.formLogin().and()  
	        .httpBasic()  
	        .and()  
	        .logout()  
	        .logoutUrl("/j_spring_security_logout") 
	        .logoutSuccessUrl("/login")  
	        ;   
	}
	
	/**
	 * Se añade la configuración de conexión a LDAP.
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.ldapAuthentication()
			 	.userSearchFilter("uid={0}")
				.contextSource()
					.url("ldap://localhost:8389/dc=example,dc=com");
	}

}