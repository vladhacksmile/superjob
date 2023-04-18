package com.vladhacksmile.searchjob.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;

    public JwtResponse(String token, Long id, String username) {
        this.id =id;
        this.token = token;
        this.username = username;
    }
}
