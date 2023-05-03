package com.vladhacksmile.searchjob.dto;

import com.vladhacksmile.searchjob.enums.ResumeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@Getter
@Setter
public class ChangeStatusDTO {
    @Positive
    private Long resumeId;
    @Positive
    private Long vacancyId;
    @NotNull
    private ResumeStatus resumeStatus;
}
