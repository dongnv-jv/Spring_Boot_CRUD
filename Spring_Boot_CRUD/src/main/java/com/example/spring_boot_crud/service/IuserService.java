package com.example.spring_boot_crud.service;

import com.example.spring_boot_crud.model.UserPojo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IuserService {
    void add(UserPojo userPojo);
    List<UserPojo> getAll();
    void delete(int id);
//    @Query("select u from UserPojo u where u.fullname like %?1%")
    List<UserPojo> findByName(String name);
    Page<UserPojo> findAll(int page,int size);
}

