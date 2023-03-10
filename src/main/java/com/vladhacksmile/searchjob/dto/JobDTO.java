package com.vladhacksmile.searchjob.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JobDTO {
    private Long userId;
    private int salary;
    private String name;
    private String information;
}
