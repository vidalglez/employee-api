package com.example.jaxrs.provider;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.example.jaxrs.exception.DataNotFoundException;
import com.example.jaxrs.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {


	@Override
	public Response toResponse(DataNotFoundException exception) {
		ErrorMessage error = new ErrorMessage(Status.NOT_FOUND.getStatusCode(), exception.getMessage());
		return Response.status(Status.NOT_FOUND).entity(error).build();
	}
}
