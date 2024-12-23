package com.ebi.employee.employee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
    private String name;
    private String address;
    private String email;
    private String phone;
    private String password;
}
