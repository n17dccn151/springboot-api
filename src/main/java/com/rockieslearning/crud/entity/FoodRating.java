package com.rockieslearning.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * Created by TanVOD on Jun, 2021
 */

@Getter
@Setter
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


}
