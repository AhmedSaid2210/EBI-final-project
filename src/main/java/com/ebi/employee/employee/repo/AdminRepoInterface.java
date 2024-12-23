package com.ebi.employee.employee.repo;

import com.ebi.employee.employee.entity.AdminEntity;
import com.ebi.employee.employee.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepoInterface extends JpaRepository<AdminEntity, Long> {

    Optional<AdminEntity> findByEmailIsAndPasswordIs(String mail, String password);
}
