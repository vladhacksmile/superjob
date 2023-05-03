package com.vladhacksmile.searchjob.dto.auth;

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
    private String refreshToken;

    public JwtResponse(String token, Long id, String username, String refreshToken) {
        this.id =id;
        this.token = token;
        this.username = username;
        this.refreshToken = refreshToken;
    }
}
