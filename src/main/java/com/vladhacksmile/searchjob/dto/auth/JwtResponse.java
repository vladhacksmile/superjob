package com.vladhacksmile.searchjob.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String refreshToken;

    public JwtResponse(String token, Long id, String username, String refreshToken) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.refreshToken = refreshToken;
    }
}
