package com.vladhacksmile.searchjob.entities;

import com.vladhacksmile.searchjob.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
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

    private String name;

    private String surname;

    private String patronymic;

    private int age;

    private String number;

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
