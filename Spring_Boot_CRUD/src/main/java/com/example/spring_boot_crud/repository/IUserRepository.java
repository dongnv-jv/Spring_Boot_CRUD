package com.example.spring_boot_crud.repository;

import com.example.spring_boot_crud.model.UserPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<UserPojo,Integer> {
    @Query("select u from UserPojo u where upper(u.fullname) like %?1%")
    List<UserPojo> findByName(String name);
}
