package com.example.jaxrs.resource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import org.springframework.beans.factory.annotation.Autowired;

import com.example.jaxrs.exception.DataNotFoundException;
import com.example.jaxrs.model.Employee;
import com.example.jaxrs.repository.EmployeeRepository;

@Path("employees")
public class EmployeeResource {

	private EmployeeRepository repository;

	@Autowired
	public EmployeeResource(EmployeeRepository repository) {
		this.repository = repository;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Employee addEmployee(Employee employee) {
		if (employee != null) {
			return repository.save(employee);
		}
		return new Employee();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getEmployees() {
		List<Employee> employees = repository.findAll().stream().filter(employee -> employee.getStatus())
				.collect(Collectors.toList());
		return employees;
	}

	@GET
	@Valid
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployee(@PathParam("id") Integer id) {
		Optional<Employee> result = repository.findById(id);
		
		if (result.isPresent() && result.get().getStatus()) {
			return Response.status(Status.OK).entity(result.get()).build();
		} else {
			throw new DataNotFoundException(String.format("Record id %d was not found", id));
		}
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Employee updateEmployee(@PathParam("id") Integer id, Employee employee) {
		if (repository.findById(id).isPresent()) {
			employee.setId(id);
			employee.setStatus(repository.findById(id).get().getStatus());
			return repository.save(employee);
		}
		return null;
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee deleteEmployee(@PathParam("id") Integer id) {
		Optional<Employee> emp = repository.findById(id);
		if (emp.isPresent()) {
			Employee employee = emp.get();
			employee.setId(id);
			employee.setStatus(false);
			return repository.save(employee);
		}
		return null;
	}
	
}
