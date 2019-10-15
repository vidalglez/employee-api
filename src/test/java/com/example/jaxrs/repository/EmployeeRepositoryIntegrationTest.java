package com.example.jaxrs.repository;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.jaxrs.model.Employee;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager em;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Before
	public void setup() {
		LocalDate birtdDate = LocalDate.of(1959, 8, 11);
		LocalDate doe = LocalDate.of(2006, 10, 10);
		Employee emp = new Employee(null, "Gustavo","Master", "Cerati", birtdDate, doe, true);
		em.persist(emp);
		em.flush();
	}
	
	
	@Test
	public void testFindEmployeeById() {
		Optional<Employee> optionalEmp = employeeRepository.findById(1);
		optionalEmp.ifPresent(employee -> {
			System.out.println("FOUND");
			assertThat(employee.getFirstName()).isEqualTo("Gustavo");
			assertThat(employee.getLastName()).isEqualTo("Cerati");
		}) ;
		if(!optionalEmp.isPresent()) {
			fail("Employee was not found");
		}
		
	}
	
	
}
