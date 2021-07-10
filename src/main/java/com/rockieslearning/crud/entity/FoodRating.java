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
    private Integer rate;

    @Column(name = "comment")
    private String comment;


    @JsonBackReference(value = "food-rating")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Food food;

    @JsonBackReference(value = "user-rating")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;


    public FoodRating() {
    }

    public FoodRating(Integer id, Integer rate, Food food, User user) {
        this.id = id;
        this.rate = rate;
        this.food = food;
        this.user = user;
    }


    public FoodRating(Integer id, Integer rate, String comment, Food food, User user) {
        this.id = id;
        this.rate = rate;
        this.comment = comment;
        this.food = food;
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
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
