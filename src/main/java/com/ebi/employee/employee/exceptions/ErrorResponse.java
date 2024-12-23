package com.ebi.employee.employee.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private String errorCode;
    private String errormessage;
    private String errorDetails;
}
