package com.vladhacksmile.searchjob.controller;

import com.vladhacksmile.searchjob.dto.JobDTO;
import com.vladhacksmile.searchjob.entities.Vacancy;
import com.vladhacksmile.searchjob.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    JobService jobService;

    @GetMapping("/{id}")
    public Vacancy getJob(@PathVariable long id) {
        return jobService.getVacancyById(id);
    }

    @PostMapping
    public ResponseEntity<?> createJob(@RequestBody JobDTO jobDTO) {
        jobService.addVacancy(jobDTO);
        return new ResponseEntity<>("Job created!", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateJob(@RequestBody JobDTO jobDTO) {
        jobService.updateVacancy(jobDTO);
        return new ResponseEntity<>("Job updated!", HttpStatus.OK);
    }
}