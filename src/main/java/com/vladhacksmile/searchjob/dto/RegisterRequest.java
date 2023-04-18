package com.vladhacksmile.searchjob.dto;

import com.vladhacksmile.searchjob.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class RegisterRequest {
    private UserRole role;
    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 2, message = "Name should be min 2 symbols!")
    private String name;
    @NotEmpty(message = "Surname should not be empty!")
    @Size(min = 2, message = "Name should be contains min 2 symbols!")
    private String surname;
    private String patronymic;
    @Min(value = 0, message = "Age should be greater than 0!")
    private int age;
    @NotEmpty(message = "Number should not be empty!")
    @Size(min = 10, message = "Number should be contains min 10 symbols!")
    private String number;
    @NotEmpty(message = "Mail should not be empty!")
    @Size(min = 5, message = "Mail should be contains min 5 symbols!")
    @Email(message = "Incorrect mail format!")
    private String mail;
    @NotEmpty(message = "Password should not be empty!")
    @Size(min = 6, message = "Password should be contains min 6 symbols!")
    private String password;
}
