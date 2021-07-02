package com.rockieslearning.crud.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
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


    @JsonManagedReference(value = "user-order")
    @OneToMany(fetch = FetchType.LAZY , mappedBy = "user")
    private Set<Order> orders = new HashSet<Order>();



    @OneToOne(fetch = FetchType.LAZY,
            mappedBy = "user")
    private Cart cart;


    @JsonManagedReference(value = "user-comment")
    @OneToMany(fetch = FetchType.EAGER , mappedBy = "user")
    private Set<FoodComment> comments = new HashSet<FoodComment>();


    @JsonManagedReference(value = "user-rating")
    @OneToMany(fetch = FetchType.EAGER , mappedBy = "user")
    private Set<FoodRating> ratings = new HashSet<FoodRating>();


    @JsonManagedReference(value = "user-detail")
    @OneToMany(fetch = FetchType.EAGER , mappedBy = "user")
    private Set<UserDetail> details = new HashSet<UserDetail>();


    public User() {

    }

    public User(Integer userId, String phone, String email, String password, String roles, Set<Order> orders, Cart cart, Set<FoodComment> comments, Set<FoodRating> ratings, Set<UserDetail> details) {
        this.userId = userId;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.orders = orders;
        this.cart = cart;
        this.comments = comments;
        this.ratings = ratings;
        this.details = details;
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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Set<FoodComment> getComments() {
        return comments;
    }

    public void setComments(Set<FoodComment> comments) {
        this.comments = comments;
    }

    public Set<FoodRating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<FoodRating> ratings) {
        this.ratings = ratings;
    }

    public Set<UserDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<UserDetail> details) {
        this.details = details;
    }
}
