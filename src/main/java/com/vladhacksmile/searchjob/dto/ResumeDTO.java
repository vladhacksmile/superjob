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
public class ResumeDTO {
//    @Min(1)
    private Long resumeId;
    @NotNull
    @NotEmpty
    private String specialization;
    @NotNull
    @NotEmpty
    private String description;
}
