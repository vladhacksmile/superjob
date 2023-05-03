package com.vladhacksmile.searchjob.dto.vacancy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
public class VacancyDTO {
    @Positive
    private Long vacancyId;
    @Min(value = 0, message = "Salary should be greater than 0!")
    private int salary;
    @NotEmpty(message = "Name should not be empty!")
    private String name;
    @NotEmpty
    @NotNull
    private String information;
}
