package com.ebi.employee.employee.repo;

import com.ebi.employee.employee.entity.EmployeeEntity;
import com.ebi.employee.employee.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepoInterface extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findAllByEmployeeEntity_Id(Long id);

}
