package com.vladhacksmile.searchjob.dto;

import com.vladhacksmile.searchjob.enums.ResumeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ChangeStatusDTO {
    private Long resumeId;
    private Long vacancyId;
    private ResumeStatus resumeStatus;
}
