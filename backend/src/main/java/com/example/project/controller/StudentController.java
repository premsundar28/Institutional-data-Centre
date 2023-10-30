package com.example.project.controller;
import com.example.project.Entity.UserInfo;
import com.example.project.Repository.StudentRepository;
import com.example.project.Services.StudentService;
import com.example.project.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.project.Repository.UserInfoRepository;
import com.example.project.Services.JwtService;
import com.example.project.model.AuthRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @GetMapping("/getBySkills")
    public ResponseEntity<List<String>> getRollNumbersBySkills(@RequestParam List<String> skills) {
        List<String> rollNumbers = studentService.getRollNumbersBySkills(skills);

        if (rollNumbers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(rollNumbers);
    }

    @PostMapping("/addStudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping("/getByInternshipRole")
    public List<String> getRollNumbersByInternshipRole(String internshipRole) {
        return studentRepository.findRollNumbersByInternshipRole(internshipRole);
    }

    @GetMapping("/getByInternshipCompany")
    public ResponseEntity<List<String>> getRollNumbersByInternshipCompany(@RequestParam String company) {
        List<String> rollNumbers = studentService.getRollNumbersByInternshipCompany(company);

        if (rollNumbers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(rollNumbers);
    }

    @GetMapping("/getByCGPA")
    public ResponseEntity<List<String>> getRollNumbersByCGPA(@RequestParam Double cgpa) {
        List<String> rollNumbers = studentService.getRollNumbersByCGPA(cgpa);

        if (rollNumbers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(rollNumbers);

    }

    @GetMapping("/getByCertificateIssuer")
    public ResponseEntity<List<String>> getRollNumbersByCertificateIssuer(@RequestParam String issuer) {
        List<String> rollNumbers = studentService.getRollNumbersByCertificateIssuer(issuer);

        if (rollNumbers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(rollNumbers);
    }

    @PostMapping("/newUser")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return studentService.addUser(userInfo);
    }


    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            UserInfo userInfo = userInfoRepository.findByUsername(authRequest.getUsername());
            String roles = userInfo.getRoles();
            return jwtService.generateToken(authRequest.getUsername(), roles);
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }



}
