package com.example.employeeManagement.config;

import org.springframework.http.HttpStatus;

/**
 * The enum SecurityApiError
 *
 * @author ALIS1
 */
public enum ServiceError implements RestError {


    NOT_VALID_EVENT(HttpStatus.BAD_REQUEST, "NOT VALIDA EVENT FOR STATUS OF USER"),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER NOT FOUND"),

    REQUEST_VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Not found request"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occured.");

    private HttpStatus httpStatus;

    /**
     * The description.
     */
    private String description;

    private ServiceError(final HttpStatus httpStatus, final String description) {
        this.httpStatus = httpStatus;
        this.description = description;
    }

    /*
     * (non-Javadoc)
     * @see com.mondiamedia.leaderboard.exception.RestError#error()
     */
    @Override
    public String error() {
        return this.name();
    }

    /*
     * (non-Javadoc)
     * @see com.mondiamedia.leaderboard.exception.RestError#httpStatus()
     */
    @Override
    public HttpStatus httpStatus() {
        return this.httpStatus;
    }

    /*
     * (non-Javadoc)
     * @see com.mondiamedia.leaderboard.exception.RestError#description()
     */
    @Override
    public String description() {
        return this.description;
    }

    /**
     * build security api exception.
     *
     * @return SecurityApiException.
     */
    public ServiceException buildException() {
        return new ServiceException(this);
    }
}
