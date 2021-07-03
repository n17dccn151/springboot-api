package com.rockieslearning.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * Created by TanVOD on Jun, 2021
 */


@Entity
@Table(name = "CART_FOODS")
public class CartFood {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_foods_seq")
    private Integer id;


    @Column(name = "amount")
    private Integer amount;




    @JsonBackReference(value = "cart-cartfood")
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name="cart_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cart cart;



//    @OneToOne(cascade = CascadeType.ALL)
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "food_id")
    private Food food;

    public CartFood() {
    }


    public CartFood( Cart cart, Food food, Integer amount) {

        this.amount = amount;
        this.cart = cart;
        this.food = food;
    }


    public CartFood(Integer id, Integer amount, Cart cart, Food food) {
        this.id = id;
        this.amount = amount;
        this.cart = cart;
        this.food = food;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
