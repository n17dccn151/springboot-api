package com.rockieslearning.crud.entity;



import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by TanVOD on Jun, 2021
 */


@Entity
@Table(name = "USERS")
public class User {


    @Id
    @GeneratedValue
    private Integer userId;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    private String roles;


    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY , mappedBy = "user")
    private Set<Order> orders = new HashSet<Order>();


    public User() {

    }

    public User(Integer userId, String phone, String email, String password, String roles) {
        this.userId = userId;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }


    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
