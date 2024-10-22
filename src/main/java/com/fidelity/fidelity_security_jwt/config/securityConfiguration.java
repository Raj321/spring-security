package com.fidelity.fidelity_security_jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.ChangeSessionIdAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import com.fasterxml.jackson.databind.introspect.AccessorNamingStrategy.Provider;

import ch.qos.logback.core.filter.Filter;

@Configuration
@EnableWebSecurity
public class securityConfiguration {

	@Autowired
	UserDetailsService customUserDetailsService;

	// this Bean is responsible for managing csrf and enable access to resources

	@Bean
	public SecurityFilterChain configureFilterChain(HttpSecurity http) throws Exception {

		http.csrf(customizer -> customizer.disable()).authorizeHttpRequests(cust -> cust.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}

//	@Bean
//	public UserDetailsService configureUserDetailsService() {
//		
//		UserDetails user1= User.withDefaultPasswordEncoder()
//				.username("raj")
//				.password("r@1234")
//				.roles("ADMIN")
//				.build();
//
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(user1);
//
//		
//
//		return manager;
//	}

	@Bean
	public AuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setUserDetailsService(customUserDetailsService);

		return provider;

	}

}
