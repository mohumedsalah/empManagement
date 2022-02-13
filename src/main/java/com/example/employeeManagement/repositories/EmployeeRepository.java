package com.example.employeeManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employeeManagement.Model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
