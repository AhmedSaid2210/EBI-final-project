package com.ebi.employee.employee.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.ControllerAdvice;
@Getter
@Setter
@AllArgsConstructor
public class EmployeeException extends RuntimeException {

    private String errorCode;
    private String errormessage;
    private String errorDetails;

}
