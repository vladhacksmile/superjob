package com.vladhacksmile.searchjob.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
@AllArgsConstructor
public class ResponseVacancyDTO {
    @Min(1)
    private Long vacancyId;
    @Min(1)
    private Long resumeId;
}
