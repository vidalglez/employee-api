package com.example.jaxrs.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.example.jaxrs.provider.DataNotFoundExceptionMapper;
import com.example.jaxrs.provider.EmployeeExceptionMapper;
import com.example.jaxrs.provider.auth.SecurityFilter;
import com.example.jaxrs.resource.EmployeeResource;

@Component
@ApplicationPath("/employee-api")
public class JerseyConfiguration extends ResourceConfig{

	public JerseyConfiguration() {
		this.register(EmployeeResource.class);
		this.register(DataNotFoundExceptionMapper.class);
		this.register(EmployeeExceptionMapper.class);
		this.register(SecurityFilter.class);
	}
}
