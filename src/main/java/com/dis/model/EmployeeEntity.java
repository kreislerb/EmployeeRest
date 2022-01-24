package com.dis.model;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMP_INT_ID_EMPLOYEE")
	private int id;

	@Column(name = "EMP_STR_NAME", length = 80, nullable = false)
	private String name;

	@Column(name = "EMP_STR_SURNAME", length = 50, nullable = false)
	private String surname;
	
	@Column(name = "EMP_CHA_GENDER", nullable = false)
	private Character gender;
	
	@Column(name = "EMP_STR_EMAIL", length = 120, nullable = false)
	private String email;
	
	@Column(name = "EMP_STR_OCUPATION", length = 50, nullable = false)
	private String ocupation;
	
	@Column(name = "EMP_DBL_SALARY", nullable = false)
	private Double salary;
	
	@Column(name = "EMP_BOL_STATUS", nullable = false)
	private Boolean status;
	
	@Transient
	public double getAnnualSalary() {
		return salary * 12;
	}
	
	
}
