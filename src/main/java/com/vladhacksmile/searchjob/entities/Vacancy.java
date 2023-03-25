package com.vladhacksmile.searchjob.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

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

    @PositiveOrZero
    private int salary;

    @NotEmpty
    @NotNull
    private String name;
    @NotEmpty
    @NotNull
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
