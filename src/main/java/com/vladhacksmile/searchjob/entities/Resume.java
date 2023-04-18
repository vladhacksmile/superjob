package com.vladhacksmile.searchjob.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "resume")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @NotNull
    private String specialization;
    private String description;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private User user;

    public Resume(String specialization, String description, User user) {
        this.specialization = specialization;
        this.description = description;
        this.user = user;
    }
}
