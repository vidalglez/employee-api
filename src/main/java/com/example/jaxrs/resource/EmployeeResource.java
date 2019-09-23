package com.example.jaxrs.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.jaxrs.dto.EmployeeDTO;
import com.example.jaxrs.mapper.EmployeeMapper;
import com.example.jaxrs.model.Employee;
import com.example.jaxrs.model.ErrorMessage;
import com.example.jaxrs.service.EmployeeService;

@Path("employees")
public class EmployeeResource {

	private EmployeeMapper mapper;
	
	private EmployeeService service;
	
	@Autowired
	public EmployeeResource(EmployeeService service, EmployeeMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEmployee(Employee employee) {	
		if (employee != null) {
			EmployeeDTO empDTO = mapper.employeeToDTO(service.addEmployee(employee));
			return Response.status(Status.CREATED).entity(empDTO).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EmployeeDTO> getEmployees() {
		List<EmployeeDTO> employees = mapper.employeeListToDTO(service.getEmployees());
		/*
		List<Employee> employees = repository.findAll().stream().filter(employee -> employee.getStatus())
				.collect(Collectors.toList());
		return employees;
		*/
		return employees;
	}

	@GET
	@Valid
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployee(@PathParam("id") Integer id) {
		/*
		Optional<Employee> result = repository.findById(id);

		if (result.isPresent() && result.get().getStatus()) {
			return Response.status(Status.OK).entity(result.get()).build();
		} else {
			throw new DataNotFoundException(String.format("Record id %d was not found", id));
		}
		*/
		
		EmployeeDTO empDTO = mapper.employeeToDTO(service.getEmployee(id));
		return Response.status(Status.FOUND).entity(empDTO).build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEmployee(@PathParam("id") Integer id, Employee employee) {
		EmployeeDTO empDTO = mapper.employeeToDTO(service.updateEmployee(id, employee));
		if(empDTO != null) {
			return Response.status(Status.FOUND).entity(empDTO).build();
		}
		return Response.status(Status.NOT_MODIFIED).build();
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEmployee(@PathParam("id") Integer id) {
		EmployeeDTO empDTO = mapper.employeeToDTO(service.deleteEmployee(id));
		if(empDTO != null) {
			return Response.status(Status.GONE).entity(empDTO).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

	@Path("/upload")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_HTML)
	public Response uploadFile(@FormDataParam("upload") InputStream is,
			@FormDataParam("upload") FormDataContentDisposition formData) {
		try {
			service.uploadEmployeesData(is);
		} catch (IOException e) {
			ErrorMessage message = new ErrorMessage();
			message.setErrorCode(Status.BAD_REQUEST.getStatusCode());
			message.setMessage(e.getMessage());
			return Response.status(Status.BAD_REQUEST).entity(message).build();
		}
		String result = String.format("File %s uploaded successfully!", formData.getFileName());
		return Response.status(Status.OK).entity(result).build();
	}

}
