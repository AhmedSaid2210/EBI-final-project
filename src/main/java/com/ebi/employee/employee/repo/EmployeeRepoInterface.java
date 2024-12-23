package com.ebi.employee.employee.repo;

import com.ebi.employee.employee.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepoInterface extends JpaRepository<EmployeeEntity, Long> {
//@Query(value = "from EmployeeEntity e where e.email=:mail And e.name=:name")
Optional<EmployeeEntity> findByEmailIsAndNameIs(String mail, String name);

Optional<EmployeeEntity> findByEmailIsAndPasswordIs(String mail, String password);

Optional<EmployeeEntity> findByEmailIsAndPhoneIs(String mail, String phone);

Optional<EmployeeEntity> findByPhone(String phone);
}
