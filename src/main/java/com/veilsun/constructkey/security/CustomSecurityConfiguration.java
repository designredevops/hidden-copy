package com.veilsun.constructkey.security;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.spel.spi.EvaluationContextExtension;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfiguration extends WebSecurityConfigurerAdapter {
 
	private static final String[] AUTH_WHITELIST = {
			"/public/*",
			"/swagger-ui/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests((authorize)->{
			authorize
				.antMatchers(AUTH_WHITELIST).permitAll()
				.anyRequest().authenticated();
		}).oauth2ResourceServer((oauth2)->{
			oauth2.jwt((jwt)->{
				jwt.jwtAuthenticationConverter(grantedAuthoritiesExtractor());
			});
		});
	}
	
	private JwtAuthenticationConverter grantedAuthoritiesExtractor() {
		 JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		 jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter((jwt) -> {
			 List<String> list = jwt.getClaimAsStringList("cognito:groups");
			 if(list == null) return new ArrayList<GrantedAuthority>();
			 return list.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toSet());
		 });
		 return jwtAuthenticationConverter;
	}


}
