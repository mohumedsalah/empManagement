package com.example.employeeManagement.config;

import lombok.Getter;

/**
 * The class RestException.
 * 
 * @author ALIS1
 *
 */
@Getter
public class RestException extends RuntimeException {

    /** The leader board errors. */
    private RestError restError;

    /**
     * Instantiates a new leader board exception.
     *
     * @param restError the rest error
     */
    public RestException(final RestError restError) {
        super(restError.description());
        this.restError = restError;
    }
}
