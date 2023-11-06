package com.example.student3.controller;

import com.example.student3.model.*;
import com.example.student3.repository.ExperienceRepository;
import com.example.student3.repository.FacultyRepository;
import com.example.student3.repository.RecentEducationRepository;
import com.example.student3.repository.SocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/Faculty")
public class FacultyController {

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    ExperienceRepository experienceRepository;

    @Autowired
    SocialRepository socialRepository;

    @Autowired
    RecentEducationRepository recentEducationRepository;

    @PostMapping("/addFaculty")
    @PreAuthorize("hasAuthority('admin','faculty')")
    public void addStudent(@RequestBody Faculty faculty) {
        facultyRepository.save(faculty);
    }

    @PostMapping("/addExperience")
    @PreAuthorize("hasAuthority('admin','faculty')")
    public void addStudent(@RequestBody Experience experience) {
        experienceRepository.save(experience);
    }

    @PostMapping("/addSocial")
    @PreAuthorize("hasAuthority('admin','faculty')")
    public void addStudent(@RequestBody Social social) {
        socialRepository.save(social);
    }

    @PostMapping("/addEducation")
    @PreAuthorize("hasAuthority('admin','faculty')")
    public void addStudent(@RequestBody RecentEducation recentEducation) {
        recentEducationRepository.save(recentEducation);
    }

    @GetMapping("/recent")
    @PreAuthorize("hasAuthority('admin')")
    public List<RecentEducation> getAllRecentEducations() {
        return recentEducationRepository.findAll();
    }

    @GetMapping("/social")
    @PreAuthorize("hasAuthority('admin')")
    public List<Social> getAllSocial() {
        return socialRepository.findAll();
    }

    @GetMapping("/experience")
    @PreAuthorize("hasAuthority('admin')")
    public List<Experience> getAllExperience(){
        return experienceRepository.findAll();
    }

    @GetMapping("/getFacultyById")
    @PreAuthorize("hasAuthority('admin')")
    public Faculty getFacultyDetailsByUserId(@RequestParam String userId) {
        return facultyRepository.findById(userId).orElse(null);
    }


    @GetMapping("/getExperienceByUserId")
    @PreAuthorize("hasAuthority('admin')")
    public List<Experience> getExperienceByUserId(@RequestParam String userId) {
        return experienceRepository.findByUserId(userId);
    }


    @GetMapping("/getFacultySocialById")
    @PreAuthorize("hasAuthority('admin')")
    public Social getFacultySocialDetailsByUserId(@RequestParam String userId) {
        return socialRepository.findByUserId(userId);
    }

    @GetMapping("/getFacultyEducationById")
    @PreAuthorize("hasAuthority('admin')")
    public RecentEducation getFacultyEducationalDetailsByUserId(@RequestParam String userId) {
        return recentEducationRepository.findByUserId(userId);
    }

    @PutMapping("/updateFaculty/{userId}")
    public ResponseEntity<Faculty> updateFaculty(
            @PathVariable String userId,
            @RequestBody Faculty updatedFaculty) {

        Optional<Faculty> existingFacultyOptional = facultyRepository.findById(userId);

        if (existingFacultyOptional.isPresent()) {
            Faculty existingFaculty = existingFacultyOptional.get();
            existingFaculty.setName(updatedFaculty.getName());
            existingFaculty.setGender(updatedFaculty.getGender());
            existingFaculty.setDob(updatedFaculty.getDob());
            existingFaculty.setDepartment(updatedFaculty.getDepartment());
            existingFaculty.setContactNumber(updatedFaculty.getContactNumber());
            existingFaculty.setAddress(updatedFaculty.getAddress());
            existingFaculty.setDesignation(updatedFaculty.getDesignation());

            facultyRepository.save(existingFaculty);

            return ResponseEntity.ok(existingFaculty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateRecentEducation/{userId}")
    public ResponseEntity<RecentEducation> updateRecentEducation(
            @PathVariable String userId,
            @RequestBody RecentEducation updatedRecentEducation) {

        Optional<RecentEducation> existingRecentEducationOptional = recentEducationRepository.findById(userId);

        if (existingRecentEducationOptional.isPresent()) {
            RecentEducation existingRecentEducation = existingRecentEducationOptional.get();
            existingRecentEducation.setType(updatedRecentEducation.getType());
            existingRecentEducation.setIssuedBy(updatedRecentEducation.getIssuedBy());

            recentEducationRepository.save(existingRecentEducation);

            return ResponseEntity.ok(existingRecentEducation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateSocial/{userId}")
    public ResponseEntity<Social> updateSocial(
            @PathVariable String userId,
            @RequestBody Social updatedSocial) {

        Optional<Social> existingSocialOptional = socialRepository.findById(userId);

        if (existingSocialOptional.isPresent()) {
            Social existingSocial = existingSocialOptional.get();
            existingSocial.setGithub(updatedSocial.getGithub());
            existingSocial.setLinkedin(updatedSocial.getLinkedin());

            socialRepository.save(existingSocial);

            return ResponseEntity.ok(existingSocial);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateExperience/{userId}")
    public ResponseEntity<Experience> updateExperience(
            @PathVariable String userId,
            @RequestBody Experience updatedExperience) {

        Optional<Experience> existingExperienceOptional = experienceRepository.findById(userId);

        if (existingExperienceOptional.isPresent()) {
            Experience existingExperience = existingExperienceOptional.get();
            existingExperience.setExperienceType(updatedExperience.getExperienceType());
            existingExperience.setExFrom(updatedExperience.getExFrom());
            existingExperience.setExTo(updatedExperience.getExTo());
            existingExperience.setCompany(updatedExperience.getCompany());
            existingExperience.setDesignation(updatedExperience.getDesignation());

            experienceRepository.save(existingExperience);

            return ResponseEntity.ok(existingExperience);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
