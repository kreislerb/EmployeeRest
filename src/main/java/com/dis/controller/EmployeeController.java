package com.dis.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.constraints.Min;

import com.dis.controller.response.EmployeeWithAnnualSalaryResponse;
import com.dis.controller.response.Response;
import com.dis.service.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	 
	@Resource
	private EmployeeService employeeService;
	
	 @GetMapping(path = "/employee/{employee_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<Response<EmployeeWithAnnualSalaryResponse>> getEmployeerById(
			 @PathVariable("employee_id") Integer emploeyeeId) {

		 Response<EmployeeWithAnnualSalaryResponse> response = employeeService.getEmployeeById(emploeyeeId);

		 return ResponseEntity.ok(response);
		 
	 }
	 
	 @GetMapping(path = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<Response<List<EmployeeWithAnnualSalaryResponse>>> getEmployeersList() {
		 
		 Response<List<EmployeeWithAnnualSalaryResponse>> response = employeeService.getAllEmployeers();
		 return ResponseEntity.ok(response);
		 
	 }
	
}
