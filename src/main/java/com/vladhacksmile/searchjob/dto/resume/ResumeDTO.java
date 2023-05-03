package com.vladhacksmile.searchjob.dto.resume;

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
public class ResumeDTO {
    @Positive
    private Long resumeId;
    @NotNull
    @NotEmpty
    private String specialization;
    @NotNull
    @NotEmpty
    private String description;
}
