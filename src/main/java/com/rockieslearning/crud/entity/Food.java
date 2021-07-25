package com.rockieslearning.crud.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.*;

/**
 * Created by TanVOD on Jun, 2021
 */

@Getter
@Setter
@Entity
@Table(name = "FOODS", indexes = @Index(name = "fn_food_index", columnList = "food_price"))
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "foods_seq")
    private Integer foodId;

    @Column(name = "food_name")

    private String name;

    @Column(name = "food_price")
    private Double price;

    @Column(name = "food_description")
    private String description;

    @Column(name = "food_quantity")
    private Integer quantity;


    @Enumerated(EnumType.STRING)
    @NaturalId(mutable=true)
    @Column(name = "food_status")
    private FoodStatusName foodStatusName;


    @JsonBackReference(value = "category-food")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;


    //    @Temporal(TemporalType.DATE)
//    @Column(name = "createdDate", nullable = false, updatable = false)
//    @CreatedDate
    @CreationTimestamp
    private Date createdDate;

    //    @Temporal(TemporalType.DATE)
//    @Column(name = "updatedDate", nullable = false)
//    @LastModifiedDate
    @UpdateTimestamp
    private Date updatedDate;


//    //18
//    @JsonIgnore
////    @OneToOne(fetch = FetchType.LAZY,
////            cascade =  CascadeType.ALL,
////            mappedBy = "food")
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "food")
//    private Set<OrderFood> orderFoods;


//    @OneToOne(fetch = FetchType.LAZY,
//            cascade =  CascadeType.ALL,
//            mappedBy = "food")


//    //18
//    @JsonIgnore
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "food")
//    private Set<CartFood> cartFoods;


    @JsonManagedReference(value = "food-image")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "food")
    private List<FoodImage> images = new ArrayList<>();

    @JsonManagedReference(value = "food-rating")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "food")
    private List<FoodRating> ratings = new ArrayList<>();


    public Food() {
    }

    public Food(Integer foodId, String name, Double price, String description, Category category, Date createdDate, Date updatedDate, Set<OrderFood> orderFoods, Set<CartFood> cartFoods, List<FoodImage> images, List<FoodRating> ratings) {
        this.foodId = foodId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        //this.orderFoods = orderFoods;
        //this.cartFoods = cartFoods;
        this.images = images;
        this.ratings = ratings;
    }

    public Food(Integer foodId, String name, Double price, String description, Integer quantity, Category category, Date createdDate, Date updatedDate, Set<OrderFood> orderFoods, Set<CartFood> cartFoods, List<FoodImage> images, List<FoodRating> ratings) {
        this.foodId = foodId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.category = category;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        //this.orderFoods = orderFoods;
        //this.cartFoods = cartFoods;
        this.images = images;
        this.ratings = ratings;
    }


//
//
//    public String getFoodStatusName() {
//        return foodStatusName;
//    }
//
//    public void setFoodStatusName(String foodStatusName) {
//        this.foodStatusName = foodStatusName;
//    }
//
//    public Set<OrderFood> getOrderFoods() {
//        return orderFoods;
//    }
//
//    public void setOrderFoods(Set<OrderFood> orderFoods) {
//        this.orderFoods = orderFoods;
//    }
//
//    public Integer getFoodId() {
//        return foodId;
//    }
//
//    public void setFoodId(Integer foodId) {
//        this.foodId = foodId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//
//    public Date getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(Date createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    public Date getUpdatedDate() {
//        return updatedDate;
//    }
//
//    public void setUpdatedDate(Date updatedDate) {
//        this.updatedDate = updatedDate;
//    }
//
////    public CartFood getCartFood() {
////        return cartFood;
////    }
////
////    public void setCartFood(CartFood cartFood) {
////        this.cartFood = cartFood;
////    }
//
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//
//    public Set<CartFood> getCartFoods() {
//        return cartFoods;
//    }
//
//    public void setCartFoods(Set<CartFood> cartFoods) {
//        this.cartFoods = cartFoods;
//    }
//
//
//    public List<FoodImage> getImages() {
//        return images;
//    }
//
//    public void setImages(List<FoodImage> images) {
//        this.images = images;
//    }
//
//    public List<FoodRating> getRatings() {
//        return ratings;
//    }
//
//    public void setRatings(List<FoodRating> ratings) {
//        this.ratings = ratings;
//    }
}
