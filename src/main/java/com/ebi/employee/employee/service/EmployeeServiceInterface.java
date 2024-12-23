package com.ebi.employee.employee.service;

import com.ebi.employee.employee.model.EmployeeDto;
import com.ebi.employee.employee.model.EmployeeSaveDto;

import java.util.List;

public interface EmployeeServiceInterface {
     List<EmployeeDto> getAllEmployees() ;

     EmployeeDto getEmployeeById(Long id) ;

     EmployeeDto getEmployeeByEmailAndName(String email, String name);

     EmployeeDto getEmployeeByEmailAndPassword(String email, String password);

     EmployeeDto getEmployeeByEmailAndPhone(String email, String phone,String password);

     EmployeeDto saveEmployee(EmployeeDto employeeDto) ;

     EmployeeSaveDto updateEmployee(EmployeeSaveDto employeeSaveDto) ;

     EmployeeSaveDto patchEmployee(EmployeeSaveDto employeeSaveDto) ;



     boolean deleteEmployee(Long id) ;
}
