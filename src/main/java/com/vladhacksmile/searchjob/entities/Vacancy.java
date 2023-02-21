package com.vladhacksmile.searchjob.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "employer_id")
    @JsonIgnore
    private User user;

    public Vacancy(int salary, String name, String information, User user) {
        this.salary = salary;
        this.name = name;
        this.information = information;
        this.user = user;
    }
}
