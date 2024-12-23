package com.ebi.employee.employee.service;

import com.ebi.employee.employee.entity.AdminEntity;
import com.ebi.employee.employee.entity.EmployeeEntity;
import com.ebi.employee.employee.exceptions.EmployeeException;
import com.ebi.employee.employee.model.AdminDto;
import com.ebi.employee.employee.model.EmployeeDto;
import com.ebi.employee.employee.repo.AdminRepoInterface;
import com.ebi.employee.employee.repo.EmployeeRepoInterface;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService implements AdminServiceInterface {

    private final AdminRepoInterface adminRepoInterface;
    private final ModelMapper modelMapper;

    public AdminDto getAdminByEmailAndPassword(String email, String password){
        Optional<AdminEntity> adminEntity = adminRepoInterface.findByEmailIsAndPasswordIs(email, password);
        if (adminEntity.isPresent()) {
            return modelMapper.map(adminEntity.get(), AdminDto.class);
        }else {
            throw new EmployeeException("400","Not Fount Admin","Please Enter valid Email and Name");
        }
    }
}
