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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int salary;

    private String name;

    private String information;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Vacancy(int salary, String name, String information, Account account) {
        this.salary = salary;
        this.name = name;
        this.information = information;
        this.account = account;
    }
}
