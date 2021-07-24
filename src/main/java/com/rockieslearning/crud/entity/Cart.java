package com.rockieslearning.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.*;

/**
 * Created by TanVOD on Jun, 2021
 */


@Getter
@Setter
@Entity
@Table(name = "CARTS",  indexes = @Index(name = "fn_cart_index", columnList = "user_id"))
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carts_seq")
    private Integer cartId;


    @JsonManagedReference(value = "cart-cartfood")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cart", cascade = CascadeType.REMOVE, orphanRemoval = true)

//    private Set<CartFood> cartFoods = new HashSet<CartFood>();

    private List<CartFood> cartFoods = new ArrayList<>();

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;


    public Cart() {
    }


    public Cart(User user) {
        this.user = user;
    }


    public Cart(Integer cartId, List<CartFood> cartFoods, User user) {
        this.cartId = cartId;
        this.cartFoods = cartFoods;
        this.user = user;
    }


//    public Integer getCartId() {
//        return cartId;
//    }
//
//    public void setCartId(Integer cartId) {
//        this.cartId = cartId;
//    }
//
//
//    public Set<CartFood> getCartFoods() {
//        return cartFoods;
//    }
//
//    public void setCartFoods(Set<CartFood> cartFoods) {
//        this.cartFoods = cartFoods;
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
