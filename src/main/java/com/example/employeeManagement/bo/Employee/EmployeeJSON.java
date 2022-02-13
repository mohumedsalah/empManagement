package com.example.employeeManagement.bo.Employee;

import com.example.employeeManagement.bo.StatusResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeJSON {

    private Integer id;

    private String name;

    private String email;

    private Integer age;

    private List<StatusResponse> status;

}
