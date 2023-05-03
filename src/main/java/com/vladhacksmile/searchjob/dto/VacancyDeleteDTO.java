package com.vladhacksmile.searchjob.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@Data
public class VacancyDeleteDTO {
    @Positive
    private Long vacancyId;
}
