package com.vladhacksmile.searchjob.controller;

import com.vladhacksmile.searchjob.dto.ResumeDTO;
import com.vladhacksmile.searchjob.dto.SearchDTO;
import com.vladhacksmile.searchjob.entities.Response;
import com.vladhacksmile.searchjob.entities.Resume;
import com.vladhacksmile.searchjob.entities.Vacancy;
import com.vladhacksmile.searchjob.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {
    @Autowired
    ResumeService resumeService;

    @GetMapping("/{id}")
    public Resume getResume(@PathVariable long id) {
        return resumeService.getResumeById(id);
    }

    @PostMapping
    public ResponseEntity<?> createResume(@RequestBody ResumeDTO resumeDTO) {
        if (resumeService.addResume(resumeDTO)) {
            return new ResponseEntity<>("Resume created!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Resume didn't create!", HttpStatus.OK);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateResume(@RequestBody ResumeDTO resumeDTO) {
        resumeService.updateResume(resumeDTO);
        return new ResponseEntity<>("Resume updated!", HttpStatus.OK);
    }

    @PostMapping("/search")
    public List<Vacancy> searchVacancy(@RequestBody SearchDTO searchDTO) {
        return resumeService.searchVacancy(searchDTO);
    }

    @GetMapping("/review/{id}")
    public List<Response> getJobReview(@PathVariable long id) {
        return resumeService.reviewing(id);
    }
}