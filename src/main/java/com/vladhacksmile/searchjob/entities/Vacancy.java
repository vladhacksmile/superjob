package com.vladhacksmile.searchjob.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "vacancy")
public class Vacancy {
    @Id
    @GeneratedValue
    private Long id;

    private int salary;

    private String name;

    private String information;
    @ManyToMany
    @JsonIgnore
    private Set<Resume> resume;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    public Vacancy(int salary, String name, String information, Set<Resume> resume, Account account) {
        this.salary = salary;
        this.name = name;
        this.information = information;
        this.resume = resume;
        this.account = account;
    }
}
