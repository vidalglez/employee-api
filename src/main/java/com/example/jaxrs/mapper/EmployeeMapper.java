package com.example.jaxrs.mapper;

import java.util.List;

import com.example.jaxrs.dto.EmployeeDTO;
import com.example.jaxrs.model.Employee;

public interface EmployeeMapper {

	EmployeeDTO employeeToDTO(Employee employee);

	List<EmployeeDTO> employeeListToDTO(List<Employee> employees);

}
