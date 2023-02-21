package com.vladhacksmile.searchjob.entities;

import com.vladhacksmile.searchjob.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "searchjob_users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private UserRole role;

    private String name;

    private String surname;

    private String patronymic;

    private int age;

    private String number;

    private String mail;

    @OneToMany(targetEntity = Resume.class, mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Resume> resume;
    @OneToMany(targetEntity = Vacancy.class, mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Vacancy> vacancy;

    public User(UserRole role, String name, String surname, String patronymic, int age, String number, String mail) {
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age = age;
        this.number = number;
        this.mail = mail;
    }
}
