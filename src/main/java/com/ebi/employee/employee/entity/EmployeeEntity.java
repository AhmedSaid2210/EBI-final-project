package com.ebi.employee.employee.entity;



import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;

    private String name;
    private String address;
    private String email;
    private String phone;
    private String password;

}
