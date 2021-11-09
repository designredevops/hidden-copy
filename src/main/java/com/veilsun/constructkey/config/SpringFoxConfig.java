package com.veilsun.constructkey.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.host("http://localhost:8080")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.veilsun.constructkey.controller"))
				.paths(PathSelectors.any())
				.build();
	}
}
