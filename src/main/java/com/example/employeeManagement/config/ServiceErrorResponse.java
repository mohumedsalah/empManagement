package com.example.employeeManagement.config;

import lombok.*;

/**
 * The class SecurityApiErrorResponse.
 * 
 * @author ALIS1
 *
 */
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ServiceErrorResponse {

    /** The description. */
    private String description;

    /** The error. */
    private String error;

    /** The status. */
    private String status;

}
