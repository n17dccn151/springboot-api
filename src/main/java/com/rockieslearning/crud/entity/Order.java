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
@Table(name = "OREDRS")
public class Order {

    @Id
    @GeneratedValue
    private Integer orderId;


    @Column(name = "order_amount")
    private String amount;

    @Column(name = "order_status")
    private String status;

    @Column(name = "order_price")
    private Double price;


    @CreationTimestamp
    private Date createdDate;


    @UpdateTimestamp
    private Date updatedDate;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER , mappedBy = "order")
    private Set<OrderFood> orderFoods = new HashSet<OrderFood>();


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name="user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;


    public Order() {
    }


    public Order(Integer orderId,  String amount, String status, Double price, Date createdDate, Date updatedDate, Set<OrderFood> orderFoods, User user) {
        this.orderId = orderId;

        this.amount = amount;
        this.status = status;
        this.price = price;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
