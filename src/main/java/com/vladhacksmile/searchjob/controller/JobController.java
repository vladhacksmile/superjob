package com.vladhacksmile.searchjob.controller;

import com.vladhacksmile.searchjob.dto.ChangeStatusDTO;
import com.vladhacksmile.searchjob.dto.JobDTO;
import com.vladhacksmile.searchjob.dto.ResponseVacancyDTO;
import com.vladhacksmile.searchjob.dto.SearchDTO;
import com.vladhacksmile.searchjob.entities.Response;
import com.vladhacksmile.searchjob.entities.Resume;
import com.vladhacksmile.searchjob.entities.Vacancy;
import com.vladhacksmile.searchjob.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
        if (jobService.addVacancy(jobDTO)) {
            return new ResponseEntity<>("Job created!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Job didn't created!", HttpStatus.OK);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateJob(@RequestBody JobDTO jobDTO) {
        jobService.updateVacancy(jobDTO);
        return new ResponseEntity<>("Job updated!", HttpStatus.OK);
    }

    @PostMapping("/response")
    public ResponseEntity<?> responseJob(@RequestBody ResponseVacancyDTO responseVacancyDTO) {
        if (jobService.responseVacancy(responseVacancyDTO)) {
            return new ResponseEntity<>("Response vacancy sent!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Response vacancy didn't send!", HttpStatus.OK);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<List<Resume>> searchVacancy(@RequestBody SearchDTO searchDTO) {
        return new ResponseEntity<>(jobService.searchResume(searchDTO), HttpStatus.OK);
    }

    @GetMapping("/review/{id}")
    public @ResponseBody List<Response> getJobReview(@PathVariable long id) {
        return jobService.reviewing(id);
    }

    @PostMapping("/status")
    public ResponseEntity<?> changeStatus(@RequestBody  ChangeStatusDTO changeStatusDTO) {
        if(jobService.changeStatus(changeStatusDTO)) {
            return new ResponseEntity<>("Status changed!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Status didn't change!", HttpStatus.OK);
        }
    }
}