package com.ebi.employee.employee.service;

import com.ebi.employee.employee.exceptions.EmployeeException;
import com.ebi.employee.employee.model.EmployeeDto;
import com.ebi.employee.employee.entity.EmployeeEntity;
import com.ebi.employee.employee.model.EmployeeSaveDto;
import com.ebi.employee.employee.repo.EmployeeRepoInterface;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService implements EmployeeServiceInterface{


    private final EmployeeRepoInterface employeeRepoInterface;
    private final ModelMapper modelMapper;

    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeEntity> employees = employeeRepoInterface.findAll();
        if (employees.isEmpty()) {
            throw new EmployeeException("400","Not Fount Employees","Please Enter valid Data to get Employees");
        }
        List<EmployeeDto> employees1= employees.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
        return employees1;
    }
    public EmployeeDto getEmployeeById(Long id) {

        Optional<EmployeeEntity>  employeeEntity = employeeRepoInterface.findById(id);
        if (employeeEntity.isPresent()) {
             return modelMapper.map(employeeEntity.get(), EmployeeDto.class);
        }else {
            throw new EmployeeException("400","Not Fount Employee","Please Enter valid Id to get the Employee");
        }
    }

    public EmployeeDto getEmployeeByEmailAndName(String email, String name) {
        Optional<EmployeeEntity> employeeEntity = employeeRepoInterface.findByEmailIsAndNameIs(email,name);
        if (employeeEntity.isPresent()) {
            return modelMapper.map(employeeEntity.get(), EmployeeDto.class);
        }else {
            throw new EmployeeException("400","Not Fount Employee","Please Enter valid Email and Name");
        }
    }
    public EmployeeDto getEmployeeByEmailAndPassword(String email, String password) {
        Optional<EmployeeEntity> employeeEntity = employeeRepoInterface.findByEmailIsAndPasswordIs(email,password);
        if (employeeEntity.isPresent()) {
            return modelMapper.map(employeeEntity.get(), EmployeeDto.class);
        }else {
            throw new EmployeeException("400","Not Fount Employee","Please Enter valid Email and Name");
        }
    }

    public EmployeeDto getEmployeeByEmailAndPhone(String email, String phone,String password) {
        Optional<EmployeeEntity> employeeEntity = employeeRepoInterface.findByEmailIsAndPhoneIs(email,phone);
        if (employeeEntity.isPresent()) {
            EmployeeSaveDto employeeSaveDto = modelMapper.map(employeeEntity.get(), EmployeeSaveDto.class);
            employeeSaveDto.setPassword(password);
            this.updateEmployee(employeeSaveDto);
            return modelMapper.map(employeeEntity.get(), EmployeeDto.class);

        }else {
            throw new EmployeeException("400","Not Fount Employee","Please Enter valid Email and Name");
        }
    }


    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        if(employeeDto.getName() == null)
        {
            throw new EmployeeException("400","Not Valid Data","Please Enter your Name to save Employee");
        }
        if(employeeDto.getAddress() == null)
        {
            throw new EmployeeException("400","Not Valid Data","Please Enter your Address to save Employee");
        }
        if(employeeDto.getEmail() == null)
        {
            throw new EmployeeException("400","Not Valid Data","Please Enter your Email to save Employee");
        }

        if(employeeDto.getPhone() == null)
        {
            throw new EmployeeException("400","Not Valid Data","Please Enter your Phone to save Employee");
        }
        if(employeeDto.getPassword() == null)
        {
            throw new EmployeeException("400","Not Valid Data","Please Enter your Salary to save Employee");
        }
        employeeRepoInterface.save(employeeEntity);
        return employeeDto;
    }

    public EmployeeSaveDto updateEmployee(EmployeeSaveDto employeeSaveDto) {
        EmployeeEntity employeeEntity =  modelMapper.map(employeeSaveDto, EmployeeEntity.class);
        if(employeeEntity.getId() == null)
        {
            throw new EmployeeException("400","Not Valid Data","Please Enter your Id to Update");
        }
        else
        {
            this.getEmployeeById(employeeSaveDto.getId());
        }
        if(employeeEntity.getName() == null)
        {
            throw new EmployeeException("400","Not Valid Data","Please Enter your Name to Update");
        }
        if(employeeEntity.getAddress() == null)
        {
            throw new EmployeeException("400","Not Valid Data","Please Enter your Address to Update");
        }
        if(employeeEntity.getEmail() == null)
        {
            throw new EmployeeException("400","Not Valid Data","Please Enter your Email to Update");
        }

        if(employeeEntity.getPhone() == null)
        {
            throw new EmployeeException("400","Not Valid Data","Please Enter your Phone to Update");
        }
        if(employeeEntity.getPassword() == null)
        {
            throw new EmployeeException("400","Not Valid Data","Please Enter your Salary to Update");
        }
        employeeRepoInterface.save(employeeEntity);
        return employeeSaveDto;
    }

    public EmployeeSaveDto patchEmployee( EmployeeSaveDto employeeSaveDto) {

        EmployeeEntity employeeEntitySave =null;
        if(employeeSaveDto != null) {

            Optional<EmployeeEntity> employeeEntity = employeeRepoInterface.findByPhone(employeeSaveDto.getPhone());


                if (employeeSaveDto.getName() != null) {

                    employeeEntity.get().setName(employeeSaveDto.getName());

                }
                if (employeeSaveDto.getAddress() != null) {

                    employeeEntity.get().setAddress(employeeSaveDto.getAddress());

                }
                if (employeeSaveDto.getEmail() != null) {

                    employeeEntity.get().setEmail(employeeSaveDto.getEmail());

                }
                if (employeeSaveDto.getPhone() != null) {

                    employeeEntity.get().setPhone(employeeSaveDto.getPhone());

                }
                if (employeeSaveDto.getPassword() != null) {

                    employeeEntity.get().setPassword(employeeSaveDto.getPassword());

                }


            employeeEntitySave = employeeRepoInterface.save(employeeEntity.get());
        }
        else {
            throw new EmployeeException("400","Not Fount Employee","Please Enter valid Data to Patch");
        }

        return modelMapper.map(employeeEntitySave, EmployeeSaveDto.class);
    }

    public boolean deleteEmployee(Long id) {

        Optional<EmployeeEntity>  employeeEntity = employeeRepoInterface.findById(id);
        if (employeeEntity.isPresent()) {
            employeeRepoInterface.deleteById(id);
            return true;
        }else {
            throw new EmployeeException("400","Not Fount Employee","Please Enter valid Id to delete");
        }

    }


}
