package com.vladhacksmile.searchjob.entities;


import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "applicant")
public class Applicant {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String surname;

    private String patronymic;

    private int age;

    private String number;

    private String mail;

    @OneToMany(targetEntity = Resume.class, mappedBy = "applicant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Resume> resume;

    public Applicant(String name, String surname, String patronymic, int age, String number, String mail, Set<Resume> resume) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age = age;
        this.number = number;
        this.mail = mail;
        this.resume = resume;
    }
}
