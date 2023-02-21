package com.vladhacksmile.searchjob.entities;

import javax.persistence.*;

import com.vladhacksmile.searchjob.enums.ResumeStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Set;


@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "resume")
public class Resume {

    @Id
    @GeneratedValue
    private Long id;
    private ResumeStatus status = ResumeStatus.REVIEW;
    private String specialization;
    private String description;

    @ManyToMany
    @JsonIgnore
    private Set<Vacancy> vacancy;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_id")
    private User user;

    public Resume(ResumeStatus status, String specialization, String description, Set<Vacancy> vacancy, User user) {
        this.status = status;
        this.specialization = specialization;
        this.description = description;
        this.vacancy = vacancy;
        this.user = user;
    }
}
