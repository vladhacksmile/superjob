package com.vladhacksmile.searchjob.entities;

import javax.persistence.*;

import com.vladhacksmile.searchjob.enums.ResumeStatus;
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
    private ResumeStatus status = ResumeStatus.REVIEW;
    private String specialization;
    private String description;
    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private User user;
}
