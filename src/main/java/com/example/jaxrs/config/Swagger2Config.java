package com.example.jaxrs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.jaxrs"))
				.paths(PathSelectors.ant("/employee-api/*"))
				.build()
				.apiInfo(apiEndPintsInfo());

	}

	private ApiInfo apiEndPintsInfo() {
		return new ApiInfoBuilder()
				.title("Spring Boot Employee REST API")
				.description("Employee Management REST API")
				.contact(new Contact("Vidal Gonzalez","http://jaxrs.example.com","darkvidal@hotmail.com"))
				.license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
				.version("1.0.0")
				.build();
	}
	
}
