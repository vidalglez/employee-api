package com.example.jaxrs.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.jaxrs.model.Employee;
import com.example.jaxrs.repository.EmployeeRepository;
import com.example.jaxrs.service.EmployeeService;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplIntegrationTest {
	
	private EmployeeService service;
	
	@Spy
	private EmployeeRepository employeeRepository;
	
	private List<Employee> employees;

	
	@Before
	public void setUp() {
		service = new EmployeeServiceImpl(employeeRepository);
		
		
		Employee[] emps = { 
			new Employee(1, "Gustavo","Master", "Cerati", LocalDate.of(1959, 8, 11), LocalDate.of(2006, 10, 10), true),
			new Employee(2, "Enrique","Master", "Bunbury", LocalDate.of(1965, 8, 11), LocalDate.of(1997, 8, 21), true),
			new Employee(3, "Rafa","Mariachi", "Dominguez", LocalDate.of(1971, 3, 9), LocalDate.of(1999, 5, 17), false),
			new Employee(4, "David","Master", "Gilmour", LocalDate.of(1951, 12, 1), LocalDate.of(1976, 4, 20), true)
			
		};
		employees = new ArrayList<>(Arrays.asList(emps));
		
	}
	
	@Test
	public void testGetEmployee() {
		Mockito.when(employeeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(employees.get(0)));
		Employee employee = service.getEmployee(1);
		assertThat("Gustavo").isEqualTo( employee.getFirstName());
		assertThat("Cerati").isEqualTo(employee.getLastName());
		assertThat(true).isEqualTo(employee.getStatus());
		assertNotNull(employee);
	}
	
	@Test
	public void testGetEmployees() {
		Mockito.when(employeeRepository.findAll()).thenReturn(employees);
		List<Employee> employeesList = service.getEmployees();
		assertThat(employeesList.size()).isEqualTo(3);
	}
}
