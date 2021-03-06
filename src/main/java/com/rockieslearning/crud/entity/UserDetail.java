package com.rockieslearning.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * Created by TanVOD on Jun, 2021
 */

@Getter
@Setter
@Entity
@Table(name = "USER_DETAIL", indexes = @Index(name = "fn_userdetail_index", columnList = "user_id"))
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_details_seq")
    private Integer id;


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING)
//    @NaturalId
    @Column(name = "status")
    private UserDetailStatusName status;


    @JsonBackReference(value = "user-detail")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public UserDetail() {
    }


    public UserDetail(Integer id, String firstName, String lastName, String phone, String address, User user) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.user = user;
    }

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
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
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
