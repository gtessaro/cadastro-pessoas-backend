package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/api/usuarios",
			            "/swagger-resources/**",  
			            "/swagger-ui.html", 
			            "/webjars/**" ,
			            "/swagger.json",
			            "/v2/api-docs",
			            "/source/**").permitAll()
				.antMatchers("/api/v1/**",
							 "/api/v2/**").authenticated()
				.anyRequest().denyAll();
	}
	
}
