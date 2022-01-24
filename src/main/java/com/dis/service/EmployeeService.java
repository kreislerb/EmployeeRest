package com.dis.service;

import java.util.Optional;

import javax.annotation.Resource;

import com.dis.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import com.dis.model.EmployeeEntity;
import com.dis.controller.response.EmployeeWithAnnualSalaryResponse;

@Service
public class EmployeeService {

	@Resource
    EmployeeRepository employeeRepository;
	
	public EmployeeWithAnnualSalaryResponse getEmployeeById(Integer id) {
		
		EmployeeWithAnnualSalaryResponse employeeResponse = new EmployeeWithAnnualSalaryResponse();
		
		Optional<EmployeeEntity> employee = employeeRepository.findById(id);
		
		if(employee.isPresent()) {
			
		return employeeResponse;
			
		}
		return null;
	}
	
	
}
