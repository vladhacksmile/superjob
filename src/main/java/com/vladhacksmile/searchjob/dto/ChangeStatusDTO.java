package com.vladhacksmile.searchjob.dto;

import com.vladhacksmile.searchjob.enums.ResumeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class ChangeStatusDTO {
    @Min(1)
    private Long resumeId;
    @Min(1)
    private Long vacancyId;
    @NotNull
    private ResumeStatus resumeStatus;
}
