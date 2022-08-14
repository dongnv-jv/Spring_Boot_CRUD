package com.example.spring_boot_crud.model;

import com.example.spring_boot_crud.validation.ValidEmails;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "sc_admin")
public class UserPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int id;
    @Column(name = "admin_username")
    @NotBlank(message = "Username must not be empty")
    @Size(min=4,message = "Length of Username must be at least 4 characters")
    private String username;
    @Transient
    @Size(max = 15, min = 6, message = "Password should be more than 6 character !")
    private String dummyPassword;
    @Column(name = "admin_password")
    private String password;
//    @ValidEmails(message = "Email is not format")
    @Email(message = "Email is not format")
    @Column(name = "email")
    private String email;
    @Column(name = "dayofbirth")
    private String dayOfBirth;
    @Column(name = "fullname")
    private String fullname;
}
