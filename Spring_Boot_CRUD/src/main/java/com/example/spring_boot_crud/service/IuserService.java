package com.example.spring_boot_crud.service;

import com.example.spring_boot_crud.model.UserPojo;

import java.util.List;

public interface IuserService {
    void add(UserPojo userPojo);
    List<UserPojo> getAll();
    void delete(int id);
}
