package com.vladhacksmile.searchjob.controller;

import com.vladhacksmile.searchjob.dto.JobDTO;
import com.vladhacksmile.searchjob.dto.ResumeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {
    @GetMapping("/{id}")
    public ResponseEntity<?> getResume(@PathVariable long id) {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createResume(@RequestBody ResumeDTO resumeDTO) {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateResume(@RequestBody ResumeDTO resumeDTO) {
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}