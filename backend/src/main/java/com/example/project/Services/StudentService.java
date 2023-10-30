package com.example.project.Services;

import com.example.project.Entity.UserInfo;
import com.example.project.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.project.Repository.UserInfoRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<String> getRollNumbersBySkills(List<String> skills) {
        return studentRepository.findRollNumbersBySkills(skills);
    }

    public List<String> getRollNumbersByInternshipCompany(String company) {
        return studentRepository.findRollNumbersByInternshipCompany(company);
    }

    public List<String> getRollNumbersByCGPA(Double cgpa) {
        return studentRepository.findRollNumbersByCGPA(cgpa);
    }

    public List<String> getRollNumbersByCertificateIssuer(String issuer) {
        return studentRepository.findRollNumbersByCertificateIssuer(issuer);
    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "user added to system ";
    }
}
