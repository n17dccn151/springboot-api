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
@Table(name = "CARTS")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carts_seq")
    private Integer cartId;


    @JsonManagedReference(value = "cart-cartfood")
    @OneToMany(fetch = FetchType.LAZY , mappedBy = "cart", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<CartFood> cartFoods = new HashSet<CartFood>();


    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;




    public Cart() {
    }


    public Cart(User user) {
        this.user = user;
    }


    public Cart(Integer cartId,  Set<CartFood> cartFoods, User user) {
        this.cartId = cartId;
        this.cartFoods = cartFoods;
        this.user = user;
    }


    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }


    public Set<CartFood> getCartFoods() {
        return cartFoods;
    }

    public void setCartFoods(Set<CartFood> cartFoods) {
        this.cartFoods = cartFoods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
