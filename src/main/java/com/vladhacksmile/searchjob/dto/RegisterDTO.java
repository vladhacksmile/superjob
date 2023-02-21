package com.vladhacksmile.searchjob.dto;

import com.vladhacksmile.searchjob.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@AllArgsConstructor
public class RegisterDTO {
    private UserRole role;
    private String name;
    private String surname;
    private String patronymic;
    private int age;
    private String number;
    private String mail;
}
