package com.ebi.employee.employee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler
    public ResponseEntity<?> employeeHandleException(EmployeeException ex){
    ErrorResponse response = new ErrorResponse(ex.getErrorCode(),ex.getErrormessage(),ex.getErrorDetails());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
}
}
