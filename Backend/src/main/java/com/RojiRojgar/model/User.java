package com.RojiRojgar.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String phone;
    private String role;
    private String password;
    private String skills;
    private String companyName;

    // Getters

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getSkills() {
        return skills;
    }

    public String getCompanyName() {
        return companyName;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}