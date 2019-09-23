package com.example.jaxrs.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jaxrs.exception.DataNotFoundException;
import com.example.jaxrs.model.Employee;
import com.example.jaxrs.repository.EmployeeRepository;
import com.example.jaxrs.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private ObjectMapper mapper;

	private EmployeeRepository repository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Employee> getEmployees() {
		List<Employee> employees = repository.findAll().stream().filter(employee -> employee.getStatus())
				.collect(Collectors.toList());
		return employees;
	}

	@Override
	public Employee getEmployee(Integer id) {
		Optional<Employee> result = repository.findById(id);

		if (result.isPresent() && result.get().getStatus()) {
			return result.get();
		} else {
			throw new DataNotFoundException(String.format("Record id %d was not found", id));
		}
	}

	@Override
	public Employee addEmployee(Employee employee) {
		if (employee != null) {
			employee.setStatus(true);
			return repository.save(employee);
		}
		return null;
	}

	@Override
	public Employee updateEmployee(Integer id, Employee employee) {
		if (repository.findById(id).isPresent()) {
			employee.setId(id);
			employee.setStatus(repository.findById(id).get().getStatus());
			return repository.save(employee);
		}
		return null;
	}

	@Override
	public Employee deleteEmployee(Integer id) {
		Optional<Employee> emp = repository.findById(id);
		if (emp.isPresent()) {
			Employee employee = emp.get();
			employee.setId(id);
			employee.setStatus(false);
			return repository.save(employee);
		}
		return null;
	}

	@Override
	public void uploadEmployeesData(InputStream is) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder jsonEmployees = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonEmployees.append(line);
		}

		List<Employee> employees = Arrays.asList(mapper.readValue(jsonEmployees.toString(), Employee[].class));
		employees.stream().forEach(employee -> {
			employee.setStatus(true);
			repository.save(employee);
		});

	}

}
