package com.dis.controller.response;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@Builder
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeWithAnnualSalaryResponse {

	private int id;
	private String name;
	private String surname;
	private Character gender;
	private String email;
	private String ocupation;
	private Double salary;
	private Double annualSalary;
	private Boolean status;
	
}
