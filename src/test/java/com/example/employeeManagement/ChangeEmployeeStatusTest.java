package com.example.employeeManagement;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.employeeManagement.Model.Employee;
import com.example.employeeManagement.bo.EventTypes;
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
public class ChangeEmployeeStatusTest {

	@InjectMocks
	private EmployeeService employeeService;

	@Mock
	private EmployeeRepository employeeRepository;

	@Test
	public void TestChangeStateToBeginCheck() {
		when(employeeRepository.getById(any())).thenReturn(exampleEmp(Status.ADDED));
		when(employeeRepository.save(any())).thenReturn(exampleEmp(Status.IN_CHECK));
		var emp = employeeService.changeEmployeeStatus(1, EventTypes.BEGIN_CHECK);
		assertNotNull(emp);
		assertEquals(emp.getStatus(),
				List.of(StatusResponse.PERMIT_CHECK_STARTED, StatusResponse.SECURITY_CHECK_STARTED));
	}

    @Test
    public void TestChangeStateToFinishedSecurity() {
        when(employeeRepository.getById(any())).thenReturn(exampleEmp(Status.IN_CHECK));
        when(employeeRepository.save(any())).thenReturn(exampleEmp(Status.IN_CHECK_SECURITY_FINISHED));
        var emp = employeeService.changeEmployeeStatus(1, EventTypes.FINISH_SECURITY_CHECK);
        assertNotNull(emp);
        assertEquals(emp.getStatus(),
                List.of(StatusResponse.PERMIT_CHECK_STARTED, StatusResponse.SECURITY_CHECK_FINISHED));
    }

    @Test
    public void TestChangeStateToFinishedPermit() {
        when(employeeRepository.getById(any())).thenReturn(exampleEmp(Status.IN_CHECK));
        when(employeeRepository.save(any())).thenReturn(exampleEmp(Status.IN_CHECK_PERMIT_FINISHED));
        var emp = employeeService.changeEmployeeStatus(1, EventTypes.FINISH_SECURITY_CHECK);
        assertNotNull(emp);
        assertEquals(emp.getStatus(),
                List.of(StatusResponse.PERMIT_CHECK_FINISHED, StatusResponse.SECURITY_CHECK_STARTED));
    }


    @Test
    public void TestChangeStateToFinishedPermitAfterFinishedPermit() {
        when(employeeRepository.getById(any())).thenReturn(exampleEmp(Status.IN_CHECK_PERMIT_FINISHED));
        when(employeeRepository.save(any())).thenReturn(exampleEmp(Status.APPROVED));
        var emp = employeeService.changeEmployeeStatus(1, EventTypes.FINISH_SECURITY_CHECK);
        assertArrayEquals(emp.getStatus().toArray(),
                List.of(StatusResponse.APPROVED).toArray());
    }


    @Test
    public void TestChangeStateToFinishedSecurityAfterFinishedSecurity() {
        when(employeeRepository.getById(any())).thenReturn(exampleEmp(Status.IN_CHECK_SECURITY_FINISHED));
        when(employeeRepository.save(any())).thenReturn(exampleEmp(Status.APPROVED));
        var emp = employeeService.changeEmployeeStatus(1, EventTypes.FINISH_WORK_PERMIT_CHECK);
        assertArrayEquals(emp.getStatus().toArray(),
                List.of(StatusResponse.APPROVED).toArray());
    }


    @Test
    public void TestChangeStateToActive() {
        when(employeeRepository.getById(any())).thenReturn(exampleEmp(Status.APPROVED));
        when(employeeRepository.save(any())).thenReturn(exampleEmp(Status.ACTIVE));
        var emp = employeeService.changeEmployeeStatus(1, EventTypes.ACTIVATE);
        assertArrayEquals(emp.getStatus().toArray(),
                List.of(StatusResponse.ACTIVE).toArray());

    }




    private Employee exampleEmp(Status ex) {
		var empMock = new Employee();
		empMock.setId(1);
		empMock.setEmail("salah@gmail.com");
		empMock.setAge(25);
		empMock.setStatus(ex);
		return empMock;
	}

}
