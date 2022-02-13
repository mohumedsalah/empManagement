package com.example.employeeManagement;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import com.example.employeeManagement.Model.Employee;
import com.example.employeeManagement.bo.Status;
import com.example.employeeManagement.bo.StatusResponse;
import com.example.employeeManagement.repositories.EmployeeRepository;
import com.example.employeeManagement.services.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * The Class FunambolServiceApplicationTest.
 * 
 * @author ALIS1
 */
@RunWith(MockitoJUnitRunner.class)
public class RetrieveEmployeeTest {


    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;


    @Test
    public void testRetrieveEmployeeWithStatusAdded()  {
        when(employeeRepository.getById(any())).thenReturn(exampleEmp(Status.ADDED));
        var emp = employeeService.getSingleEmployee(1);
        assertNotNull(emp);
        assertEquals(emp.getId(), 1);
        assertEquals(emp.getStatus(), List.of(Status.ADDED));
    }



    @Test
    public void testRetrieveEmployeeWithStatusCheckIn() {
        when(employeeRepository.getById(any())).thenReturn(exampleEmp(Status.IN_CHECK));
        var emp = employeeService.getSingleEmployee(1);
        assertNotNull(emp);
        assertEquals(emp.getId(), 1);
        assertEquals(emp.getStatus(),
                List.of(StatusResponse.PERMIT_CHECK_STARTED, StatusResponse.SECURITY_CHECK_STARTED ));
    }

    @Test
    public void testRetrieveEmployeeWithStatusCheckInPermitFinished() {
        when(employeeRepository.getById(any())).thenReturn(exampleEmp(Status.IN_CHECK_PERMIT_FINISHED));
        var emp = employeeService.getSingleEmployee(1);
        assertNotNull(emp);
        assertEquals(emp.getId(), 1);
        assertEquals(emp.getStatus(),
                List.of(StatusResponse.PERMIT_CHECK_FINISHED, StatusResponse.SECURITY_CHECK_STARTED ));
    }

    @Test
    public void testRetrieveEmployeeWithStatusCheckInSecurityFinished() {
        when(employeeRepository.getById(any())).thenReturn(exampleEmp(Status.IN_CHECK_SECURITY_FINISHED));
        var emp = employeeService.getSingleEmployee(1);
        assertNotNull(emp);
        assertEquals(emp.getId(), 1);
        assertEquals(emp.getStatus(),
                List.of(StatusResponse.PERMIT_CHECK_STARTED, StatusResponse.SECURITY_CHECK_FINISHED ));
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
