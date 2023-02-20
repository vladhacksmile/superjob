package com.vladhacksmile.searchjob.controller;

import com.vladhacksmile.searchjob.dto.JobDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job")
public class JobController {

    @GetMapping("/{id}")
    public ResponseEntity<?> getJob(@PathVariable long id) {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createJob(@RequestBody JobDTO jobDTO) {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateJob(@RequestBody JobDTO jobDTO) {
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}