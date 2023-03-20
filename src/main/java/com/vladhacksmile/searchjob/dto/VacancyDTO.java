package com.vladhacksmile.searchjob.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class VacancyDTO {
    private Long userId;
    @Min(value = 0, message = "Salary should be greater than 0!")
    private int salary;
    @NotEmpty(message = "Name should not be empty!")
    private String name;
    private String information;
}
