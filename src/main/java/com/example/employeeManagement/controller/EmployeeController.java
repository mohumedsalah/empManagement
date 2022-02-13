package com.example.employeeManagement.controller;

import org.springframework.web.bind.annotation.*;

import com.example.employeeManagement.bo.EventTypes;
import com.example.employeeManagement.bo.Employee.CreateEmployeeJSON;
import com.example.employeeManagement.bo.Employee.EmployeeJSON;
import com.example.employeeManagement.services.EmployeeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private final EmployeeService employeeService;

	@GetMapping("/{id}")
	public EmployeeJSON getEmployee(@PathVariable Integer id) {
		var employees = employeeService.getSingleEmployee(id);
		return employees;
	}

	@PostMapping
	public EmployeeJSON CreateEmployee(@RequestBody CreateEmployeeJSON employeeJSON) {
		return employeeService.addEmployee(employeeJSON);

	}

	@PutMapping
	public EmployeeJSON changeEmployeeStatus(@RequestParam Integer employeeId, @RequestParam EventTypes eventTypes) {
		return employeeService.changeEmployeeStatus(employeeId, eventTypes);
	}
}
