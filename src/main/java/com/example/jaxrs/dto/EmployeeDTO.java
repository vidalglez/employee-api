package com.example.jaxrs.dto;

import java.time.LocalDate;

import com.example.jaxrs.model.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
	
	private Integer id;
	
	private String firstName;
	
	private String middleInitial;
	
	private String lastName;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonProperty("dateOfBirth")
	private LocalDate DateOfBirth;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonProperty("dateOfEmployment")
	private LocalDate DateOfEmployment;
	
	private Status status;
}
