package com.example.student3.controller;

import com.example.student3.model.*;
import com.example.student3.repository.ExperienceRepository;
import com.example.student3.repository.FacultyRepository;
import com.example.student3.repository.RecentEducationRepository;
import com.example.student3.repository.SocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PreAuthorize("hasAuthority('admin')")
    public void addStudent(@RequestBody Faculty faculty) {
        facultyRepository.save(faculty);
    }

    @PostMapping("/addExperience")
    @PreAuthorize("hasAuthority('admin')")
    public void addStudent(@RequestBody Experience experience) {
        experienceRepository.save(experience);
    }

    @PostMapping("/addSocial")
    @PreAuthorize("hasAuthority('admin')")
    public void addStudent(@RequestBody Social social) {
        socialRepository.save(social);
    }

    @PostMapping("/addEducation")
    @PreAuthorize("hasAuthority('admin')")
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


}
