package com.vladhacksmile.searchjob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class MailDTO {


    private ArrayList<String> list;

    public MailDTO(ArrayList<String> list) {
        this.list=list;
    }
}