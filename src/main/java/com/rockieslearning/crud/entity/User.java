package com.rockieslearning.crud.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by TanVOD on Jun, 2021
 */

@Getter
@Setter
@Entity

// uniqueConstraints = {
//@UniqueConstraint(columnNames = {
//        "phone_number"
//}),
//@UniqueConstraint(columnNames = {
//        "email"
//}

@Table(name = "USERS", indexes = @Index(name = "fn_user_index", columnList = "phone_number"))
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    private Long userId;


    @NotBlank
    @Column(name = "phone_number")
    private String phone;

    @Column(name = "email")
//    @NaturalId(mutable=true)
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;


    @Column(name = "password")
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @JsonManagedReference(value = "user-order")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Order> orders = new HashSet<Order>();


    @OneToOne(fetch = FetchType.LAZY,
            mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;


    @JsonManagedReference(value = "user-rating")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<FoodRating> ratings = new HashSet<FoodRating>();


    @JsonManagedReference(value = "user-detail")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<UserDetail> details = new HashSet<UserDetail>();


    public User() {

    }


    public User(String phone, String email, String password) {
        this.phone = phone;
        this.email = email;
        this.password = password;
    }


    public User(String phone, String email, String password, Set<Role> roles) {
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }


//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }
//
//    public Set<Order> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(Set<Order> orders) {
//        this.orders = orders;
//    }
//
//    public Cart getCart() {
//        return cart;
//    }
//
//    public void setCart(Cart cart) {
//        this.cart = cart;
//    }
//
//
//    public Set<FoodRating> getRatings() {
//        return ratings;
//    }
//
//    public void setRatings(Set<FoodRating> ratings) {
//        this.ratings = ratings;
//    }
//
//    public Set<UserDetail> getDetails() {
//        return details;
//    }
//
//    public void setDetails(Set<UserDetail> details) {
//        this.details = details;
//    }
}
