package com.vladhacksmile.searchjob.dto.vacancy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
@Data
@Getter
@Setter
public class VacancyDeleteDTO {
    @Positive
    private Long vacancyId;
}
