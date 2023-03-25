package com.vladhacksmile.searchjob.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@AllArgsConstructor
public class SearchDTO {
    @NotNull
    @NotEmpty
    private String name;
    @PositiveOrZero
    private int offset;
}
