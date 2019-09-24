package com.example.jaxrs.dto;

import java.time.LocalDate;

import com.example.jaxrs.model.Link;
import com.example.jaxrs.model.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "id", "firstName", "middleInitial", "lastName", "status", "dateOfBirth", "dateOfEmployment", "link" })
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

	private Link links;
}
