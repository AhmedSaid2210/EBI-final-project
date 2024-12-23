package com.ebi.employee.employee.service;

import com.ebi.employee.employee.model.TaskDto;
import com.ebi.employee.employee.model.TaskSaveDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TaskServiceInterface {

    List<TaskDto> getAllTasks() ;

    List<TaskDto> getAllTasksById(Long id) ;

    TaskDto setTask(TaskDto taskDto);

    TaskSaveDto updateTask(TaskSaveDto taskSaveDto);

    boolean deleteTask( Long id);

}
