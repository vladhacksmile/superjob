package com.vladhacksmile.searchjob.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;


@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "resume")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String specialization;
    private String description;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Resume(String specialization, String description, Account account) {
        this.specialization = specialization;
        this.description = description;
        this.account = account;
    }
}
