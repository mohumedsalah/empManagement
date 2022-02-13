package com.example.employeeManagement;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import com.example.employeeManagement.bo.Employee.CreateEmployeeJSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.employeeManagement.Model.Employee;
import com.example.employeeManagement.bo.Status;
import com.example.employeeManagement.bo.StatusResponse;
import com.example.employeeManagement.repositories.EmployeeRepository;
import com.example.employeeManagement.services.EmployeeService;

/**
 * The Class FunambolServiceApplicationTest.
 * 
 * @author ALIS1
 */
@RunWith(MockitoJUnitRunner.class)
public class AddEmployeeTest {


    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;


    @Test
    public void TestAddEmployee()  {
        when(employeeRepository.save(any())).thenReturn(exampleEmp(Status.ADDED));
        CreateEmployeeJSON n = new CreateEmployeeJSON();
        n.setAge(25);
        n.setEmail("salah@gmail.com");
        n.setName("omar");
        var emp = employeeService.addEmployee(n);
        assertNotNull(emp);
        assertEquals(emp.getId(), 1);
        assertEquals(emp.getStatus(), List.of(Status.ADDED));
    }





    private Employee exampleEmp(Status ex){
        var empMock =  new Employee();
        empMock.setId(1);
        empMock.setEmail("salah@gmail.com");
        empMock.setAge(25);
        empMock.setStatus(ex);
        return empMock;
    }


}
