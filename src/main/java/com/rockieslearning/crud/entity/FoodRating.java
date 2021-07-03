package com.rockieslearning.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * Created by TanVOD on Jun, 2021
 */

@Entity
@Table(name = "FOOD_RATINGS")
public class FoodRating {



    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_ratings_seq")
    private Integer id;

    @Column(name = "rate")
    private String rate;



    @JsonBackReference(value = "food-rating")
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name="food_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Food food;

    @JsonBackReference(value = "user-rating")
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name="user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;


    public FoodRating() {
    }

    public FoodRating(Integer id, String rate, Food food, User user) {
        this.id = id;
        this.rate = rate;
        this.food = food;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
