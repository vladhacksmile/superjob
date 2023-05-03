package com.vladhacksmile.searchjob.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
public class ResponseVacancyDTO {
    @Positive
    private Long vacancyId;
    @Positive
    private Long resumeId;
}
