package com.dis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import com.dis.controller.response.Response;
import com.dis.exceptions.EmployeeException;
import com.dis.exceptions.ExceptionType;
import com.dis.exceptions.GenericException;
import com.dis.repository.EmployeeRepository;
import com.dis.util.messages.DefaultMessages;
import com.dis.util.messages.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.dis.model.EmployeeEntity;
import com.dis.controller.response.EmployeeWithAnnualSalaryResponse;

@Service
@Slf4j
public class EmployeeService {

	@Resource
    EmployeeRepository employeeRepository;
	
	public Response<EmployeeWithAnnualSalaryResponse> getEmployeeById(Integer id) {

		Response<EmployeeWithAnnualSalaryResponse> response = new Response<EmployeeWithAnnualSalaryResponse>();
		try{
			Optional<EmployeeEntity> employee = employeeRepository.findById(id);

			if(employee.isPresent()) {
				EmployeeWithAnnualSalaryResponse employeeResponse = EmployeeWithAnnualSalaryResponse.builder()
						.id(employee.get().getId())
						.name(employee.get().getName())
						.surname(employee.get().getSurname())
						.salary(employee.get().getSalary())
						.annualSalary(employee.get().getAnnualSalary())
						.status(employee.get().getStatus())
						.email(employee.get().getEmail())
						.gender(employee.get().getGender())
						.build();

				response.setData(employeeResponse);
				response.setMessage(DefaultMessages.SUCESS_REQUEST.getMessage());
				response.setStatus(ResponseStatus.OK.getStatus());
				return response;
			}
			else{
				throw new EmployeeException(String.format("the employee with id %s was not found", id));
			}
		}
		catch (EmployeeException e){
			throw e;
		}
		catch (Exception e) {
			log.error(String.format("Employee Service: ERROR type %s", ExceptionType.DIS_EXCEPTION.getType()), e);
			throw new GenericException(e);
		}

	}

	public Response<List<EmployeeWithAnnualSalaryResponse>> getAllEmployeers() {

		Response<List<EmployeeWithAnnualSalaryResponse>> response = new Response<>();
		try{


		List<EmployeeEntity> employeeList = employeeRepository.findAll();

		List<EmployeeWithAnnualSalaryResponse> listEmployeersResponse = new ArrayList<>();

		for (EmployeeEntity employee: employeeList) {
			EmployeeWithAnnualSalaryResponse employeeResponse = EmployeeWithAnnualSalaryResponse.builder()
					.id(employee.getId())
					.name(employee.getName())
					.surname(employee.getSurname())
					.salary(employee.getSalary())
					.annualSalary(employee.getAnnualSalary())
					.status(employee.getStatus())
					.email(employee.getEmail())
					.gender(employee.getGender())
					.build();

			listEmployeersResponse.add(employeeResponse);

		}

		response.setData(listEmployeersResponse);
		response.setMessage(DefaultMessages.SUCESS_REQUEST.getMessage());
		response.setStatus(ResponseStatus.OK.getStatus());
		return response;
		}
		catch (Exception e) {
			log.error(String.format("Employee Service: ERROR type %s", ExceptionType.DIS_EXCEPTION.getType()), e);
			throw new GenericException(e);
		}
	}


	
}
