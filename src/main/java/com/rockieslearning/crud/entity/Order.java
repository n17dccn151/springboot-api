package com.rockieslearning.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by TanVOD on Jun, 2021
 */
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
    private Integer orderId;


//    @Column(name = "order_amount")
//    private Integer amount;

    @Column(name = "order_status")
    private String status;

//    @Column(name = "order_price")
//    private Double price;


    @CreationTimestamp
    private Date createdDate;


    @UpdateTimestamp
    private Date updatedDate;

    @JsonManagedReference(value = "order-orderfood")
    @OneToMany(fetch = FetchType.LAZY , mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderFood> orderFoods = new HashSet<OrderFood>();


    @JsonBackReference(value = "user-order")
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name="user_id")
    private User user;






    public Order() {
    }

    public Order(Integer orderId, String status, Date createdDate, Date updatedDate, Set<OrderFood> orderFoods, User user) {
        this.orderId = orderId;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.orderFoods = orderFoods;
        this.user = user;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Set<OrderFood> getOrderFoods() {
        return orderFoods;
    }

    public void setOrderFoods(Set<OrderFood> orderFoods) {
        this.orderFoods = orderFoods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
