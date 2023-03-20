package com.vladhacksmile.searchjob.entities;

import com.vladhacksmile.searchjob.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

//    @OneToMany(targetEntity = Resume.class, mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonIgnore
//    private Set<Resume> resume;
//    @OneToMany(targetEntity = Vacancy.class, mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonIgnore
//    private Set<Vacancy> vacancy;

    public Account(UserRole role, String name, String surname, String patronymic, int age, String number, String mail) {
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age = age;
        this.number = number;
        this.mail = mail;
//        this.resume = resume;
//        this.vacancy = vacancy;
    }
}
