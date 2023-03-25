package com.vladhacksmile.searchjob.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class VacancyDTO {
    @Min(1)
    private Long userId;
    @Min(1)
    private Long vacancyId;
    @Min(value = 0, message = "Salary should be greater than 0!")
    private int salary;
    @NotEmpty(message = "Name should not be empty!")
    private String name;
    @NotEmpty
    @NotNull
    private String information;
}
