package com.example.jaxrs.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_employee")
	//@Min(value=1, message="Id must be greater than zero")
	//@Size(min = 1, message="Id must be greater than zero")
	private Integer id;
	
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "middleinitial")
	private String middleInitial;
	
	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "dateofbirth", columnDefinition = "DATE")
	private LocalDate DateOfBirth;
	
	@Column(name = "dateofemployment", columnDefinition = "DATE")
	private LocalDate DateOfEmployment;
	
	@Column(name="status")
	private Boolean status;
}
