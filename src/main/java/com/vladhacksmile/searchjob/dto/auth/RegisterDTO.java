package com.vladhacksmile.searchjob.dto.auth;

import com.vladhacksmile.searchjob.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class RegisterDTO {
    @NotNull
    private UserRole role;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String surname;
    private String patronymic;
    @Min(18)
    private int age;
    @NotNull
    @NotEmpty
    private String number;
    @Email
    private String mail;
}
