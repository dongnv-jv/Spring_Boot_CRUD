package com.example.spring_boot_crud.repository;

import com.example.spring_boot_crud.model.UserPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserPojo,Integer> {
}
