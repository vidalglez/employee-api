package com.example.jaxrs.provider.auth;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.jaxrs.model.ErrorMessage;

@Provider
@Component
public class SecurityFilter implements ContainerRequestFilter {

	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	private static final String SECURED_URL_PREFIX = "employees";
	
	@Value("${auth.user}")
	private String authUser;
	
	@Value("${auth.passwd}")
	private String authPwd;
	
	
	

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {
			List<String> headers = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			if (headers != null && headers.size() > 0) {
				String authToken = headers.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				String decodedString = Base64.decodeAsString(authToken);
				StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
				String username = tokenizer.nextToken();
				String password = tokenizer.nextToken();
				if (authUser.equals(username) && authPwd.equals(password)) {
					return;
				}
			}
		}
		if ("DELETE".equals(requestContext.getMethod())) {
			ErrorMessage errorMsg = new ErrorMessage(Status.UNAUTHORIZED.getStatusCode(),
					"User is unauthorized for this resource");
			Response unauthorizedResponse = Response.status(Status.UNAUTHORIZED).entity(errorMsg)
					.type(MediaType.APPLICATION_JSON).build();
			requestContext.abortWith(unauthorizedResponse);
		}
	}

}
