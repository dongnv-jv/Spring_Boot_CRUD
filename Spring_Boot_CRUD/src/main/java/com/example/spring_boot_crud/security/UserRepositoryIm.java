package com.example.spring_boot_crud.security;

import com.example.spring_boot_crud.model.UserPojo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryIm extends JpaRepository<UserPojo,Integer> {
    UserPojo findByUsername(String username);

}
