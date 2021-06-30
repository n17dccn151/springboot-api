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
@Table(name = "OREDR_FOODS")
public class OrderFood {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "price")
    private Double price;

    @Column(name = "amount")
    private Integer amount;




//    @JsonManagedReference
//    @OneToMany(fetch = FetchType.LAZY , mappedBy = "food")
//    private Set<OrderFood> orderFoods = new HashSet<OrderFood>();


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name="order_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Order order;


    public OrderFood() {
    }

    public OrderFood(Integer id, Double price, Integer amount, Order order) {
        this.id = id;
        this.price = price;
        this.amount = amount;
        this.order = order;
    }


    public OrderFood(Double price, Integer amount, Order order) {
        this.price = price;
        this.amount = amount;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
