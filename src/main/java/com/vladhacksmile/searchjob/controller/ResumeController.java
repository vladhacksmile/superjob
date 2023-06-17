package com.vladhacksmile.searchjob.controller;

//@RestController
//@RequestMapping("/api/resumes")
public class ResumeController {
//    final ResumeService resumeService;
//
//    public ResumeController(ResumeService resumeService) {
//        this.resumeService = resumeService;
//    }
//
//    @GetMapping("/{id}")
//    public Resume getResume(@PathVariable long id) {
//        return resumeService.getResumeById(id);
//    }
//
//    @DeleteMapping
//    public ResponseEntity<?> deleteResume(@AuthenticationPrincipal User user, @RequestBody ResumeDeleteDTO resumeDeleteDTO) {
//        if(resumeService.deleteResumeById(user, resumeDeleteDTO.getResumeId())) {
//            return new ResponseEntity<>("Resume removed!", HttpStatus.OK);
//        }
//        return new ResponseEntity<>("Resume didn't remove!", HttpStatus.BAD_REQUEST);
//    }
//
//    @PostMapping
//    public ResponseEntity<?> createResume(@AuthenticationPrincipal User user, @RequestBody ResumeDTO resumeDTO) {
//        Resume resume = resumeService.addResume(user, resumeDTO);
//        if (resume != null) {
//            return new ResponseEntity<>(resume, HttpStatus.OK);
//        }
//        return new ResponseEntity<>("Resume didn't create!", HttpStatus.BAD_REQUEST);
//    }
//
//    @PutMapping
//    public ResponseEntity<?> updateResume(@AuthenticationPrincipal User user, @RequestBody ResumeDTO resumeDTO) {
//        if(resumeService.updateResume(user, resumeDTO)) {
//            return new ResponseEntity<>("Resume updated!", HttpStatus.OK);
//        }
//        return new ResponseEntity<>("Resume didn't update!", HttpStatus.BAD_REQUEST);
//    }
//
//    @PostMapping("/search")
//    public List<Vacancy> searchVacancy(@RequestBody SearchDTO searchDTO) {
//        return resumeService.searchVacancy(searchDTO);
//    }
//
//    @GetMapping("/review/{id}")
//    public List<Response> getJobReview(@PathVariable long id) {
//        return resumeService.reviewing(id);
//    }
}