package com.example.spring_boot_crud.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "sc_admin")
public class UserPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int id;
    @Column(name = "admin_username")
    private String username;
    @Column(name = "admin_password")
    private String password;

}
