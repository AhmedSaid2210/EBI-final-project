package com.ebi.employee.employee.controller;

import com.ebi.employee.employee.exceptions.EmployeeException;
import com.ebi.employee.employee.model.AdminDto;
import com.ebi.employee.employee.model.EmployeeDto;
import com.ebi.employee.employee.model.GeneralResponse;
import com.ebi.employee.employee.service.AdminServiceInterface;
import com.ebi.employee.employee.service.EmployeeServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminServiceInterface adminServiceInterface;
    @Value("${success.message}")
    String successMessage;
    @Value("${success.code}")
    String successCode;

    @GetMapping("/{email}/{password}")
    @ResponseBody
    public ResponseEntity<?> getAdmin(@PathVariable String email, @PathVariable String password) {
        AdminDto admin = adminServiceInterface.getAdminByEmailAndPassword(email,password);

        GeneralResponse<AdminDto> response = new GeneralResponse<>(successCode,successMessage,admin);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/loginAdmin")
    public String loginAdmin(Model model) {
        model.addAttribute("employee",new AdminDto());
        return "admin";
    }
    @PostMapping("/loginAdmin")
    public String loginAdmin(AdminDto adminDto,Model model) {
        AdminDto AdminDto1 =  adminServiceInterface.getAdminByEmailAndPassword(adminDto.getEmail(),adminDto.getPassword());
        if (AdminDto1 == null) {
            throw new EmployeeException("400","Not Fount Employee","Please Enter valid Email and Name");
        }
        return "redirect:/employee/home";
    }
}
