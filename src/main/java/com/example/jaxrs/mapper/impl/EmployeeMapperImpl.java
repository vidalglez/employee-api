package com.example.jaxrs.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.jaxrs.dto.EmployeeDTO;
import com.example.jaxrs.mapper.EmployeeMapper;
import com.example.jaxrs.model.Employee;
import com.example.jaxrs.model.Status;

@Component
public class EmployeeMapperImpl implements EmployeeMapper {

	@Override
	public EmployeeDTO employeeToDTO(Employee employee) {
		EmployeeDTO empDTO = new EmployeeDTO();
		empDTO.setId(employee.getId());
		empDTO.setDateOfBirth(employee.getDateOfBirth());
		empDTO.setDateOfEmployment(employee.getDateOfEmployment());
		empDTO.setFirstName(employee.getFirstName());
		empDTO.setLastName(employee.getLastName());
		empDTO.setMiddleInitial(employee.getMiddleInitial());
		empDTO.setStatus(employee.getStatus() != null && employee.getStatus() ? Status.ACTIVE : Status.INACTIVE);
		return empDTO;
	}

	@Override
	public List<EmployeeDTO> employeeListToDTO(List<Employee> employees) {
		return employees.stream().map(employee -> this.employeeToDTO(employee)).collect(Collectors.toList());
	}

}
