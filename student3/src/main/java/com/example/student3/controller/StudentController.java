package com.example.student3.controller;

import com.example.student3.model.*;
import com.example.student3.repository.*;
import com.example.student3.service.JwtService;
import com.example.student3.Entity.UserInfo;
import com.example.student3.service.StudentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/Student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private StudentQuery service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    InternshipRepository internshipRepository;

    @Autowired
    CertificateRepository certificateRepository;


    @PostMapping("addStudent")
    @PreAuthorize("hasAuthority('admin','student')")
    public void addStudent(@RequestBody Student student) {
        studentRepository.save(student);
    }

    @PostMapping("addSkill")
    @PreAuthorize("hasAuthority('admin','student')")
    public void addSkill(@RequestBody Skill skill){
        skillRepository.save(skill);
    }

    @PostMapping("addProject")
    @PreAuthorize("hasAuthority('admin','student')")
    public void addProject(@RequestBody Project project){
        projectRepository.save(project);
    }

    @PostMapping("addInternship")
    @PreAuthorize("hasAuthority('admin','student')")
    public void addInternship(@RequestBody Internship internship){
        internshipRepository.save(internship);
    }

    @PostMapping("addCertificate")
    @PreAuthorize("hasAuthority('admin','student')")
    public void addCertification(@RequestBody Certification certification){
        certificateRepository.save(certification);
    }

    @GetMapping("showAllStudents")
    @PreAuthorize("hasAuthority('admin')")
    public List<Student> showAllStudents() {
        return studentRepository.findAll();
    }


    @GetMapping("/getStudentById")
    @PreAuthorize("hasAuthority('admin')")
    public Student getStudentDetailsByUserId(@RequestParam String studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    @GetMapping("/studentsByCGPA")
    @PreAuthorize("hasAuthority('admin')")
    public List<Student> getStudentsByCGPA(@RequestParam("cgpa") String cgpa) {
        return studentRepository.findByCGPAGreaterThanEqual(cgpa);
    }

    @GetMapping("/studentIdsBySkill")
    @PreAuthorize("hasAuthority('admin')")
    public List<String> getStudentIdsBySkill(@RequestParam("skill") String skill) {
        List<Skill> skillsWithMatchingName = skillRepository.findBySkill(skill);
        List<String> studentIds = new ArrayList<>();

        for (Skill s : skillsWithMatchingName) {
            studentIds.add(s.getStudentId());
        }

        return studentIds;
    }

    @PostMapping("/newUser")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return service.addUser(userInfo);
    }


    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }


    }

    @GetMapping("/studentIdsByName")
    @PreAuthorize("hasAuthority('admin')")
    public List<String> getStudentIdsByName(@RequestParam("name") String name) {
        List<Certification> certificatesWithMatchingName = certificateRepository.findByName(name);
        List<String> studentIds = new ArrayList<>();

        for (Certification c : certificatesWithMatchingName) {
            studentIds.add(c.getStudentId());
        }

        return studentIds;
    }


    @GetMapping("/studentIdsByInternshipDomain")
    @PreAuthorize("hasAuthority('admin')")
    public List<String> getStudentIdsByInternshipDomain(@RequestParam("domain") String domain) {
        List<Internship> internshipsWithMatchingDomain = internshipRepository.findByDomain(domain);
        List<String> studentIds = new ArrayList<>();

        for (Internship internship : internshipsWithMatchingDomain) {
            studentIds.add(internship.getStudentId());
        }

        return studentIds;
    }

    @PutMapping("/updateStudent/{studentId}")
    @PreAuthorize("hasAuthority('admin','student')")
    public ResponseEntity<String> updateStudent(@PathVariable String studentId, @RequestBody Student updatedStudent) {
        Student existingStudent = studentRepository.findById(studentId).orElse(null);

        if (existingStudent != null) {
            // Update the student information
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setBatch(updatedStudent.getBatch());
            existingStudent.setEmailId(updatedStudent.getEmailId());
            existingStudent.setMobile(updatedStudent.getMobile());
            existingStudent.setCGPA(updatedStudent.getCGPA());

            studentRepository.save(existingStudent);
            return ResponseEntity.ok("Student information updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateSkill/{studentId}")
    @PreAuthorize("hasAuthority('admin','student')")
    public ResponseEntity<String> updateSkill(@PathVariable String studentId, @RequestBody Skill updatedSkill) {
        Skill existingSkill = skillRepository.findById(studentId).orElse(null);

        if (existingSkill != null) {
            existingSkill.setSkill(updatedSkill.getSkill());
            existingSkill.setDomain(updatedSkill.getDomain());

            skillRepository.save(existingSkill);
            return ResponseEntity.ok("Skill information updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }



        @PutMapping("/updateProject/{studentId}")
        public ResponseEntity<Project> updateProject(
                @PathVariable String studentId,
                @RequestBody Project updatedProject) {

            // Check if the project with the provided studentId exists in the database
            Optional<Project> existingProjectOptional = projectRepository.findById(studentId);

            if (existingProjectOptional.isPresent()) {
                Project existingProject = existingProjectOptional.get();
                existingProject.setDescription(updatedProject.getDescription());
                existingProject.setTags(updatedProject.getTags());
                existingProject.setUrl(updatedProject.getUrl());
                existingProject.setVerificationURL(updatedProject.getVerificationURL());

                // Save the updated project back to the database
                projectRepository.save(existingProject);

                return ResponseEntity.ok(existingProject);
            } else {
                // If the project doesn't exist, return a 404 Not Found response
                return ResponseEntity.notFound().build();
            }


        }

    @PutMapping("/updateInternship/{studentId}")
    public ResponseEntity<Internship> updateInternship(
            @PathVariable String studentId,
            @RequestBody Internship updatedInternship) {

        Optional<Internship> existingInternshipOptional = internshipRepository.findById(studentId);

        if (existingInternshipOptional.isPresent()) {
            Internship existingInternship = existingInternshipOptional.get();
            existingInternship.setName(updatedInternship.getName());
            existingInternship.setCompany(updatedInternship.getCompany());
            existingInternship.setStartDate(updatedInternship.getStartDate());
            existingInternship.setEndDate(updatedInternship.getEndDate());
            existingInternship.setVerification(updatedInternship.getVerification());
            existingInternship.setDomain(updatedInternship.getDomain());


            internshipRepository.save(existingInternship);

            return ResponseEntity.ok(existingInternship);
        } else {

            return ResponseEntity.notFound().build();
        }



    }

    @PutMapping("/updateCertification/{studentId}")
    public ResponseEntity<Certification> updateCertification(
            @PathVariable String studentId,
            @RequestBody Certification updatedCertification) {

        // Check if the certification with the provided studentId exists in the database
        Optional<Certification> existingCertificationOptional = certificateRepository.findById(studentId);

        if (existingCertificationOptional.isPresent()) {
            Certification existingCertification = existingCertificationOptional.get();
            existingCertification.setName(updatedCertification.getName());
            existingCertification.setExpire(updatedCertification.getExpire());
            existingCertification.setVerification(updatedCertification.getVerification());
            existingCertification.setCertify(updatedCertification.getCertify());

            // Save the updated certification back to the database
            certificateRepository.save(existingCertification);

            return ResponseEntity.ok(existingCertification);
        } else {
            // If the certification doesn't exist, return a 404 Not Found response
            return ResponseEntity.notFound().build();
        }


    }

    @DeleteMapping("/deleteStudent/{studentId}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<String> deleteStudent(@PathVariable String studentId) {
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
            return ResponseEntity.ok("Student deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteSkill/{studentId}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<String> deleteSkill(@PathVariable String studentId) {
        if (skillRepository.existsById(studentId)) {
            skillRepository.deleteById(studentId);
            return ResponseEntity.ok("Skill deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteProject/{studentId}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<String> deleteProject(@PathVariable String studentId) {
        if (projectRepository.existsById(studentId)) {
            projectRepository.deleteById(studentId);
            return ResponseEntity.ok("Project deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/deleteInternship/{studentId}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<String> deleteInternship(@PathVariable String studentId) {
        if (internshipRepository.existsById(studentId)) {
            internshipRepository.deleteById(studentId);
            return ResponseEntity.ok("Internship deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/deleteCertification/{studentId}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<String> deleteCertification(@PathVariable String studentId) {
        if (certificateRepository.existsById(studentId)) {
            certificateRepository.deleteById(studentId);
            return ResponseEntity.ok("Certification deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
