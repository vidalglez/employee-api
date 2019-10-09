package com.example.jaxrs.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.example.jaxrs.provider.DataNotFoundExceptionMapper;
import com.example.jaxrs.provider.EmployeeExceptionMapper;
import com.example.jaxrs.provider.auth.SecurityFilter;
import com.example.jaxrs.resource.EmployeeResource;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.SwaggerConfigLocator;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@Component
@ApplicationPath("/employee-api")
public class JerseyConfiguration extends ResourceConfig{

	public JerseyConfiguration() {
		this.register(EmployeeResource.class);
		this.register(DataNotFoundExceptionMapper.class);
		this.register(EmployeeExceptionMapper.class);
		this.register(SecurityFilter.class);
		this.register(MultiPartFeature.class);
		
		BeanConfig swaggerConfig = new BeanConfig();
		swaggerConfig.setBasePath("/employee-api");
		//swaggerConfig.setHost("http://localhost:8080");
		
		SwaggerConfigLocator.getInstance().putConfig(SwaggerContextService.CONFIG_ID_DEFAULT, swaggerConfig);
		
		packages(getClass().getPackage().getName(), ApiListingResource.class.getPackage().getName());
		
		//register(swaggerConfig);

	    register(ApiListingResource.class);
	    register(SwaggerSerializers.class);
		
	}
}
