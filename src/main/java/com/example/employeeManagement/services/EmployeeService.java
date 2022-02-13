package com.example.employeeManagement.services;

import java.util.List;
import java.util.Optional;

import com.example.employeeManagement.bo.Employee.CreateEmployeeJSON;
import com.example.employeeManagement.bo.Employee.EmployeeJSON;
import com.example.employeeManagement.bo.EventTypes;
import com.example.employeeManagement.bo.Status;
import com.example.employeeManagement.bo.StatusResponse;
import com.example.employeeManagement.config.ServiceError;
import org.springframework.stereotype.Service;

import com.example.employeeManagement.Model.Employee;
import com.example.employeeManagement.repositories.EmployeeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeService {

	EmployeeRepository employeeRepository;

	public EmployeeJSON getSingleEmployee(Integer id) {
		var empOp = returnEmployeeById(id);
		if(empOp != null)
			throw ServiceError.USER_NOT_FOUND.buildException();
		return mapEmpToJSON(empOp);
	}

	public EmployeeJSON addEmployee(CreateEmployeeJSON employeeJSON){
		var emp = mapJsonToEmp(employeeJSON);
		emp.setStatus(Status.ADDED);
		return mapEmpToJSON(employeeRepository.save(emp));
	}

	public EmployeeJSON changeEmployeeStatus(Integer empId, EventTypes eventType){
		var emp = returnEmployeeById(empId);
		if(emp == null)
			throw ServiceError.USER_NOT_FOUND.buildException();

		var lastStatus = emp.getStatus();
		switch (eventType){

			case BEGIN_CHECK:
				if(emp.getStatus().equals(Status.ADDED))
					emp.setStatus(Status.IN_CHECK);
				break;
			case FINISH_SECURITY_CHECK:
				if(emp.getStatus().equals(Status.IN_CHECK))
					emp.setStatus(Status.IN_CHECK_SECURITY_FINISHED);
				else if(emp.getStatus().equals(Status.IN_CHECK_PERMIT_FINISHED))
					emp.setStatus(Status.APPROVED);
				break;
			case FINISH_WORK_PERMIT_CHECK:
				if(emp.getStatus().equals(Status.IN_CHECK))
					emp.setStatus(Status.IN_CHECK_PERMIT_FINISHED);
				else if(emp.getStatus().equals(Status.IN_CHECK_SECURITY_FINISHED))
					emp.setStatus(Status.APPROVED);
				break;
			case ACTIVATE:
				if(emp.getStatus().equals(Status.APPROVED))
					emp.setStatus(Status.ACTIVE);
				break;
		}
		if(emp.getStatus().equals(lastStatus)){
			throw ServiceError.NOT_VALID_EVENT.buildException();
		}
		return mapEmpToJSON(employeeRepository.save(emp));
	}



	/******************private section**********************/

	private Employee mapJsonToEmp (CreateEmployeeJSON employeeJSON ){
		var employee = new Employee();
		employee.setName(employeeJSON.getName());
		employee.setAge(employeeJSON.getAge());
		employee.setEmail(employeeJSON.getEmail());
		return employee;
	}

	private EmployeeJSON mapEmpToJSON (Employee employee){
		var json = new EmployeeJSON();
		json.setId(employee.getId());
		json.setAge(employee.getAge());
		json.setEmail(employee.getEmail());
		json.setName(employee.getName());
		if(employee.getStatus() != null && employee.getStatus().equals(Status.IN_CHECK)){
			json.setStatus(List.of(StatusResponse.PERMIT_CHECK_STARTED,StatusResponse.SECURITY_CHECK_STARTED));
		}else if(employee.getStatus() != null && employee.getStatus().equals(Status.IN_CHECK_PERMIT_FINISHED)){
			json.setStatus(List.of(StatusResponse.PERMIT_CHECK_FINISHED,StatusResponse.SECURITY_CHECK_STARTED));
		}else if(employee.getStatus() != null && employee.getStatus().equals(Status.IN_CHECK_SECURITY_FINISHED)){
			json.setStatus(List.of(StatusResponse.PERMIT_CHECK_STARTED,StatusResponse.SECURITY_CHECK_FINISHED));
		}
		else{
			json.setStatus(List.of(StatusResponse.valueOf(employee.getStatus().name())));
		}
		return json;
	}

	private Employee returnEmployeeById(Integer id){
		return returnEmployeeById(id, false);
	}

	private Employee returnEmployeeById(Integer id,boolean throwException ){
		try{
			return employeeRepository.getById(id);
		}catch (Exception e){
			if(throwException)
				throw e;
			return null;
		}
	}


}
