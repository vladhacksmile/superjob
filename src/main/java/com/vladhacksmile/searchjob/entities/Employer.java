package com.vladhacksmile.searchjob.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "employer")
public class Employer {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String information;

    @OneToMany(targetEntity = Vacancy.class, mappedBy = "employer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Vacancy> vacancy;

    public Employer(String name, String information, Set<Vacancy> vacancy) {
        this.name = name;
        this.information = information;
        this.vacancy = vacancy;
    }
}
