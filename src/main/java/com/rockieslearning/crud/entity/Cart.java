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
    @GeneratedValue
    private Integer cartId;


    @Column(name = "amount")
    private Integer amount;



    @JsonManagedReference(value = "cart-cartfood")
    @OneToMany(fetch = FetchType.EAGER , mappedBy = "cart")
    private Set<CartFood> cartFoods = new HashSet<CartFood>();


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;




    public Cart() {
    }

    public Cart(Integer cartId, Integer amount, Set<CartFood> cartFoods, User user) {
        this.cartId = cartId;
        this.amount = amount;
        this.cartFoods = cartFoods;
        this.user = user;
    }


    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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
