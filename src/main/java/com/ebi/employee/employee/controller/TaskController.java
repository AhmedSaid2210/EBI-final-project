package com.ebi.employee.employee.controller;

import com.ebi.employee.employee.model.EmployeeDto;
import com.ebi.employee.employee.model.GeneralResponse;
import com.ebi.employee.employee.model.TaskDto;
import com.ebi.employee.employee.model.TaskSaveDto;
import com.ebi.employee.employee.service.TaskService;
import com.ebi.employee.employee.service.TaskServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskServiceInterface taskServiceInterface;
    @Value("${success.message}")
    String successMessage;
    @Value("${success.code}")
    String successCode;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getAllTasks() {
        List<TaskDto> tasks = taskServiceInterface.getAllTasks();
        GeneralResponse<List<TaskDto>> response = new GeneralResponse<>(successCode,successMessage,tasks);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getAllTasksById(@PathVariable Long id) {
        List<TaskDto> tasks = taskServiceInterface.getAllTasksById(id);
        GeneralResponse<List<TaskDto>> response = new GeneralResponse<>(successCode,successMessage,tasks);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> setTask(@RequestBody TaskDto taskDto)
    {
        TaskDto task = taskServiceInterface.setTask(taskDto);
        GeneralResponse<TaskDto> response = new GeneralResponse<>(successCode,successMessage,task);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping
    @ResponseBody
    public ResponseEntity<?> updateTask(@RequestBody TaskSaveDto taskSaveDto)
    {
        TaskSaveDto task = taskServiceInterface.updateTask(taskSaveDto);
        GeneralResponse<TaskSaveDto> response = new GeneralResponse<>(successCode,successMessage,task);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteTask(@PathVariable Long id)
    {
        boolean result = taskServiceInterface.deleteTask(id);
        GeneralResponse<Boolean> response = new GeneralResponse<>(successCode,successMessage,result);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/tasksEmpoyee")
    public String getAllTasksByIdEmploye(Model model) {

        model.addAttribute("employee",new TaskSaveDto());

        return "getTask";
    }


    @GetMapping("/tasks")
    public String getAllTasksByIdEmployee(Model model) {

        model.addAttribute("employee",new TaskSaveDto());

        return "getTask";
    }
    @PostMapping("/tasks")
    public String getAllTasksByIdEemployee(Model model,TaskSaveDto taskSaveDto) {
        List<TaskDto> tasks = taskServiceInterface.getAllTasksById(taskSaveDto.getId());
        GeneralResponse<List<TaskDto>> response = new GeneralResponse<>(successCode,successMessage,tasks);
        model.addAttribute("response",response);
        return "tasks";
    }

}
