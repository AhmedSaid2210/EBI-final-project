package com.ebi.employee.employee.service;

import com.ebi.employee.employee.entity.EmployeeEntity;
import com.ebi.employee.employee.entity.TaskEntity;
import com.ebi.employee.employee.model.TaskDto;
import com.ebi.employee.employee.model.TaskSaveDto;
import com.ebi.employee.employee.repo.EmployeeRepoInterface;
import com.ebi.employee.employee.repo.TaskRepoInterface;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService implements TaskServiceInterface {

    private final TaskRepoInterface taskRepoInterface;
    private final ModelMapper modelMapper ;
    private final EmployeeRepoInterface employeeRepoInterface;
    public List<TaskDto> getAllTasks() {
        List<TaskEntity> taskEntities = taskRepoInterface.findAll();

        return taskEntities.stream().map(taskEntity -> modelMapper.map(taskEntity,TaskDto.class)).collect(Collectors.toList());
    }

    public TaskDto setTask(TaskDto taskDto)
    {
        TaskEntity taskEntity = modelMapper.map(taskDto, TaskEntity.class);
        taskRepoInterface.save(taskEntity);
        return modelMapper.map(taskEntity, TaskDto.class);
    }

    public TaskSaveDto updateTask(TaskSaveDto taskSaveDto)
    {
        TaskSaveDto taskSaveDto2 = null;

        if(taskSaveDto != null)
        {
            Optional<TaskEntity> taskEntityOptional = taskRepoInterface.findById(taskSaveDto.getId());

            if(taskEntityOptional.isPresent()) {

                if (taskSaveDto.getTitle() != null) {
                    taskEntityOptional.get().setTitle(taskSaveDto.getTitle());
                }
                if (taskSaveDto.getDescription() != null) {
                    taskEntityOptional.get().setDescription(taskSaveDto.getDescription());
                }
                if (taskSaveDto.getDate() != null) {
                    taskEntityOptional.get().setDate(taskSaveDto.getDate());
                }
                if (taskSaveDto.getEmployeeId() != null) {
                    Optional<EmployeeEntity> employeeEntity = employeeRepoInterface.findById(taskSaveDto.getEmployeeId());
                    if (employeeEntity.isPresent()) {
                        taskEntityOptional.get().setEmployeeEntity(employeeEntity.get());
                    }
                }
                TaskEntity savedTaskEntity = taskRepoInterface.save(taskEntityOptional.get());


                taskSaveDto2 = modelMapper.map(savedTaskEntity, TaskSaveDto.class);
            }

        }
        return taskSaveDto2;
    }
    public boolean deleteTask(Long id) {
        Optional<TaskEntity> taskEntityOptional = taskRepoInterface.findById(id);
        if(taskEntityOptional.isPresent()) {
            taskRepoInterface.delete(taskEntityOptional.get());

        }
        return true;
    }
    public List<TaskDto> getAllTasksById(Long id)
    {
        List<TaskEntity> taskEntities = taskRepoInterface.findAllByEmployeeEntity_Id(id);

        return taskEntities.stream().map(taskEntity -> modelMapper.map(taskEntity,TaskDto.class)).collect(Collectors.toList());

    }
}
