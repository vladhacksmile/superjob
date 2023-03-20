package com.vladhacksmile.searchjob.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResumeDTO {
    private Long userId;
    private Long resumeId;
    private String specialization;
    private String description;
}
