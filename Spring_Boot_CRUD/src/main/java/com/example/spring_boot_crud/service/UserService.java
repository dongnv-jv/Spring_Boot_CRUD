package com.example.spring_boot_crud.service;

import com.example.spring_boot_crud.model.UserPojo;
import com.example.spring_boot_crud.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IuserService {
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public void add(UserPojo userPojo) {
        iUserRepository.save(userPojo);
    }
    @Override
    public List<UserPojo> getAll() {
        List<UserPojo> list = new ArrayList<>();
        iUserRepository.findAll().forEach(list::add);
        return list;
    }
    @Override
    public void delete(int id) {
        iUserRepository.deleteById(id);
    }

    @Override
    public List<UserPojo> findByName(String name) {
        return iUserRepository.findByName(name);
    }
}
