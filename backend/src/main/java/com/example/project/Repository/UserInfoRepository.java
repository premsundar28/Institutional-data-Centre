package com.example.project.Repository;

import com.example.project.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {
    Optional<UserInfo> findByusername(String username);

    UserInfo findByUsername(String username);

}