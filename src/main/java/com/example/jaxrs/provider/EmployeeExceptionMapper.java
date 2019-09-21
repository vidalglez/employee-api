package com.example.jaxrs.provider;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EmployeeExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		return Response.status(Status.BAD_REQUEST).entity(this.createMessage(exception))
				.type(MediaType.APPLICATION_JSON).build();
	}

	public String createMessage(ConstraintViolationException exception) {
		StringBuilder message = new StringBuilder();
		for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
			message.append(String.format("%s %s", cv.getPropertyPath(), cv.getMessage()));
		}
		return message.toString();
	}

}
