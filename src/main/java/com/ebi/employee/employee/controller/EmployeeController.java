package com.ebi.employee.employee.controller;

import com.ebi.employee.employee.exceptions.EmployeeException;
import com.ebi.employee.employee.model.EmployeeDto;
import com.ebi.employee.employee.model.EmployeeSaveDto;
import com.ebi.employee.employee.model.GeneralResponse;
import com.ebi.employee.employee.service.EmployeeServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {


    private final EmployeeServiceInterface employeeServiceInterface;

    @Value("${success.message}")
    String successMessage;
    @Value("${success.code}")
    String successCode;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getAllEmployees() {
        List<EmployeeDto> employees = employeeServiceInterface.getAllEmployees();
        GeneralResponse< List<EmployeeDto> > response = new GeneralResponse<>(successCode,successMessage,employees);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        EmployeeDto emp = employeeServiceInterface.getEmployeeById(id);
        GeneralResponse<EmployeeDto> response = new GeneralResponse<>(successCode,successMessage,emp);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto emp = employeeServiceInterface.saveEmployee(employeeDto);
        GeneralResponse<EmployeeDto> response = new GeneralResponse<>(successCode,successMessage,emp);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping
    @ResponseBody
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeSaveDto employeeSaveDto) {
        EmployeeSaveDto emp = employeeServiceInterface.updateEmployee(employeeSaveDto);
        GeneralResponse<EmployeeSaveDto> response = new GeneralResponse<>(successCode,successMessage,emp);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
   @PatchMapping
   @ResponseBody
    public ResponseEntity<?> patchEmployee(@RequestBody EmployeeSaveDto employeeSaveDto) {
        EmployeeSaveDto emp = employeeServiceInterface.patchEmployee(employeeSaveDto);
        GeneralResponse<EmployeeSaveDto> response =  new GeneralResponse<>(successCode,successMessage,emp);
        return new ResponseEntity<>(response, HttpStatus.OK);
   }
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        Boolean emp = employeeServiceInterface.deleteEmployee(id);
        GeneralResponse<Boolean> response = new GeneralResponse<>(successCode,successMessage,emp);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }









    @GetMapping("/welcome")
    public String welcome(Model model) {

        return "welcome";
    }

    @GetMapping("/loginEmployee")
    public String loginAdmin(Model model) {
        model.addAttribute("employee",new EmployeeDto());
        return "employee";
    }
    @PostMapping("/loginEmployee")
    public String loginAdmin(EmployeeDto employeeDto,Model model) {
        EmployeeDto employeeDto1 =  employeeServiceInterface.
                getEmployeeByEmailAndPassword(employeeDto.getEmail(),employeeDto.getPassword());


        if (employeeDto1 == null) {
            throw new EmployeeException("400","Not Fount Employee","Please Enter valid Email and Name");
        }
        return "redirect:/task/tasksEmpoyee";
    }


    @GetMapping("/forgotPassword")
    public String forgotPassword(Model model) {
        model.addAttribute("employee",new EmployeeDto());
        return "forgotPassword";
    }
    @PostMapping("/forgotPassword")
    public String forgotPassword(EmployeeDto employeeDto,Model model) {
        employeeServiceInterface.getEmployeeByEmailAndPhone(employeeDto.getEmail(),employeeDto.getPhone(),employeeDto.getPassword());
        return "redirect:/employee/loginEmployee";
    }

    @GetMapping("/home")
    public String home(Model model) {

        return "home2";
    }

    @GetMapping("/view")
    public String getAllEmployeesView(Model model) {
        List<EmployeeDto> employees = employeeServiceInterface.getAllEmployees();
        GeneralResponse<List<EmployeeDto>> response = new GeneralResponse<>(successCode,successMessage,employees);
        model.addAttribute("response",response);
        return "index";
    }



    @GetMapping("/register")
    public String saveEmployeesView(Model model) {
        model.addAttribute("employee",new EmployeeDto());
        return "addEmployee";
    }
    @PostMapping("/register")
    public String saveEmployeesView(EmployeeDto employeeDto,Model model) {
        employeeServiceInterface.saveEmployee(employeeDto);
        model.addAttribute("employee",new EmployeeDto());
        return "redirect:/employee/view";
    }




    @GetMapping("/delete")
    public String deleteEmployeeById(Model model) {
        model.addAttribute("employee",new EmployeeSaveDto());
        return "deleteEmployee";
    }
    @PostMapping("/delete")
    public String deleteEmployeeById(EmployeeSaveDto employeeSaveDto,Model model) {
        employeeServiceInterface.deleteEmployee(employeeSaveDto.getId());
        return "redirect:/employee/view";
    }

    @GetMapping("/update")
    public String updateEmployee(Model model) {
        model.addAttribute("employee",new EmployeeSaveDto());
        return "updateEmployee";
    }
    @PostMapping("/update")
    public String updateEmployee(EmployeeSaveDto employeeSaveDto,Model model) {
        employeeServiceInterface.updateEmployee(employeeSaveDto);
        return "redirect:/employee/view";
    }

    @GetMapping("/get")
    public String getEmployee(Model model) {
        model.addAttribute("employee",new EmployeeSaveDto());
        return "findEmployee";
    }
    @PostMapping("/get")
    public String getEmployee(EmployeeSaveDto employeeSaveDto,Model model) {
        EmployeeDto employeeDto = employeeServiceInterface.getEmployeeById(employeeSaveDto.getId());
        GeneralResponse<EmployeeDto> response = new GeneralResponse<>(successCode,successMessage,employeeDto);
        model.addAttribute("response",response);
        return "getEmployee";
    }


}
