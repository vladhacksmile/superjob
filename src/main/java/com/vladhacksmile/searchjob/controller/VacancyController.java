package com.vladhacksmile.searchjob.controller;

import com.vladhacksmile.searchjob.dto.*;
import com.vladhacksmile.searchjob.dto.resume.ChangeStatusDTO;
import com.vladhacksmile.searchjob.dto.vacancy.ResponseVacancyDTO;
import com.vladhacksmile.searchjob.dto.vacancy.VacancyDTO;
import com.vladhacksmile.searchjob.dto.vacancy.VacancyDeleteDTO;
import com.vladhacksmile.searchjob.entities.Response;
import com.vladhacksmile.searchjob.entities.Resume;
import com.vladhacksmile.searchjob.entities.User;
import com.vladhacksmile.searchjob.entities.Vacancy;
import com.vladhacksmile.searchjob.service.VacancyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<?> createVacancy(@AuthenticationPrincipal User user, @RequestBody @Valid VacancyDTO vacancyDTO, BindingResult bindingResult) {
        Vacancy vacancy = vacancyService.addVacancy(user, vacancyDTO);
        if (vacancy  != null && !bindingResult.hasErrors()) {
            return new ResponseEntity<>(vacancy, HttpStatus.OK);
        }
        return new ResponseEntity<>("Vacancy didn't created! " + bindingResult.toString(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<?> updateVacancy(@AuthenticationPrincipal User user, @RequestBody VacancyDTO vacancyDTO) {
        if (vacancyService.updateVacancy(user, vacancyDTO)) {
            return new ResponseEntity<>(vacancyDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>("Vacancy didn't update!", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteVacancy(@AuthenticationPrincipal User user, @RequestBody VacancyDeleteDTO vacancyDeleteDTO) {
        if(vacancyService.deleteVacancyById(user, vacancyDeleteDTO.getVacancyId())) {
            return new ResponseEntity<>("Vacancy removed!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Vacancy didn't remove!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/response")
    public ResponseEntity<?> responseVacancy(@RequestBody ResponseVacancyDTO responseVacancyDTO) {
        if (vacancyService.responseVacancy(responseVacancyDTO)) {
            return new ResponseEntity<>("Response sent!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Response vacancy didn't send!", HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Resume>> searchVacancy(@RequestBody SearchDTO searchDTO) {
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