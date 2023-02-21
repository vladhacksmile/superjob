package com.vladhacksmile.searchjob.entities;

import javax.persistence.*;
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
    @GeneratedValue
    private Long id;

    private String specialization;
    private String description;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    public Resume(String specialization, String description, Applicant applicant) {
        this.description = description;
        this.specialization = specialization;
        this.applicant = applicant;
    }
}
