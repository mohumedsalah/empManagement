package com.example.employeeManagement.config;

import lombok.Getter;

/**
 * The class SecurityApiException.
 * 
 * @author ALIS1
 *
 */
@Getter
public class ServiceException extends RestException{

    /** The security api error. */
    private ServiceError serviceError;

    /**
     * instantiate new securityApiException.
     *
     * @param serviceError
     */
    public ServiceException(final ServiceError serviceError) {
        super(serviceError);
        this.serviceError = serviceError;
    }
}
