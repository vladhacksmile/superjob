package com.vladhacksmile.searchjob.entities;

import com.vladhacksmile.searchjob.enums.ResumeStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name="responses")
@Setter
@Getter
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resume_id")
    private Resume resume;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;
    private ResumeStatus resumeStatus = ResumeStatus.REVIEW;

    public Response(Resume resume, Vacancy vacancy, ResumeStatus resumeStatus) {
        this.resume = resume;
        this.vacancy = vacancy;
        this.resumeStatus = resumeStatus;
    }
}

