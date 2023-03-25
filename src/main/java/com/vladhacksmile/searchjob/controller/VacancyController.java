package com.vladhacksmile.searchjob.controller;

import com.vladhacksmile.searchjob.dto.*;
import com.vladhacksmile.searchjob.entities.Response;
import com.vladhacksmile.searchjob.entities.Resume;
import com.vladhacksmile.searchjob.entities.Vacancy;
import com.vladhacksmile.searchjob.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/vacancies")
public class VacancyController {

    final VacancyService vacancyService;

    public VacancyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping("/{id}")
    public Vacancy getVacancy(@PathVariable long id) {
        return vacancyService.getVacancyById(id);
    }

    @PostMapping
    public ResponseEntity<?> createVacancy(@RequestBody @Valid VacancyDTO vacancyDTO, BindingResult bindingResult) {
        if (vacancyService.addVacancy(vacancyDTO) && !bindingResult.hasErrors()) {
            return new ResponseEntity<>("Vacancy created!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Vacancy didn't created! " + bindingResult.toString(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<?> updateVacancy(@RequestBody VacancyDTO vacancyDTO) {
        if (vacancyService.updateVacancy(vacancyDTO)) {
            return new ResponseEntity<>("Vacancy updated!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Vacancy didn't update!", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteVacancy(@RequestBody VacancyDeleteDTO vacancyDeleteDTO) {
        if(vacancyService.deleteVacancyById(vacancyDeleteDTO.getVacancyId())) {
            return new ResponseEntity<>("Vacancy removed!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Vacancy didn't remove!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/response")
    public ResponseEntity<?> responseVacancy(@RequestBody ResponseVacancyDTO responseVacancyDTO) {
        if (vacancyService.responseVacancy(responseVacancyDTO)) {
            return new ResponseEntity<>("Response vacancy sent!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Response vacancy didn't send!", HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<Set<Resume>> searchVacancy(@RequestBody SearchDTO searchDTO) {
        return new ResponseEntity<>(vacancyService.searchResume(searchDTO), HttpStatus.OK);
    }

    @GetMapping("/review/{id}")
    public @ResponseBody List<Response> getVacancyReview(@PathVariable long id) {
        return vacancyService.reviewing(id);
    }

    @PostMapping("/status")
    public ResponseEntity<?> changeStatus(@RequestBody ChangeStatusDTO changeStatusDTO) {
        if(vacancyService.changeStatus(changeStatusDTO)) {
            return new ResponseEntity<>("Status changed!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Status didn't change!", HttpStatus.OK);
    }
}