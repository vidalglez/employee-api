package com.example.jaxrs.service.impl;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.jaxrs.model.Employee;
import com.example.jaxrs.repository.EmployeeRepository;
import com.example.jaxrs.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
//@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplIntegrationTest {
	
	@Autowired
	//@MockBean
	//@InjectMocks
	private EmployeeService service;
	
	@MockBean(name = "employeeRepository")
	//private EmployeeRepository employeeRepository;
	static private EmployeeRepository employeeRepository;
	
	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration{
		
		@Bean
		public EmployeeService employeeService() {
			//return Mockito.mock(EmployeeServiceImpl.class);
			return new EmployeeServiceImpl(employeeRepository);
		}
		
		@Bean
		public ObjectMapper mapper() {
			return Mockito.mock(ObjectMapper.class);
			//return new ObjectMapper();
		}
	}
	
	
	@Before
	public void setUp() {
		LocalDate birtdDate = LocalDate.of(1959, 8, 11);
		LocalDate doe = LocalDate.of(2006, 10, 10);
		Employee emp = new Employee(1, "Gustavo","Master", "Cerati", birtdDate, doe, true);
		
		Mockito.when(employeeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(emp));
	}
	
	@Test
	public void testGetEmployee() {
		Employee employee = service.getEmployee(1);
		//assertNull(employee);
		assertNotNull(employee);
	}
}
