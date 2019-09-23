package com.example.jaxrs.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.example.jaxrs.model.Employee;

public interface EmployeeService {
	
	public List<Employee> getEmployees();
	
	public Employee getEmployee(Integer id);
	
	public Employee addEmployee(Employee employee);
	
	public Employee updateEmployee(Integer id, Employee employee);
	
	public Employee deleteEmployee(Integer id);
	
	public void uploadEmployeesData(InputStream is) throws IOException;

}
