package com.ebi.employee.employee.service;

import com.ebi.employee.employee.model.AdminDto;
import com.ebi.employee.employee.model.EmployeeDto;

public interface AdminServiceInterface {
    AdminDto getAdminByEmailAndPassword(String email, String password);
}
