package com.vladhacksmile.searchjob.dto.auth;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
public class AuthRequest {
    @NotEmpty(message = "Mail should not be empty!")
    @Size(min = 5, message = "Mail should be contains min 5 symbols!")
    @Email(message = "Incorrect mail format!")
    private String mail;
    @NotEmpty(message = "Password should not be empty!")
    @Size(min = 6, message = "Password should be contains min 6 symbols!")
    private String password;
}
