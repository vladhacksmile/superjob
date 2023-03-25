package com.vladhacksmile.searchjob.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class SearchDTO {
    @NotNull
    @NotEmpty
    private String name;
    private int offset;
}
