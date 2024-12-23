package com.ebi.employee.employee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskSaveDto {
    private Long id;
    private String title;
    private String description;
    private String date;
    private Long employeeId;
}
