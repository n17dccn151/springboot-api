package com.rockieslearning.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * Created by TanVOD on Jun, 2021
 */

@Entity
@Table(name = "FOOD_COMMENTS")
public class FoodComment {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_comments_seq")
    private Integer id;

    @Column(name = "comment")
    private String comment;





    @JsonBackReference(value = "food-comment")
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name="food_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Food food;

    @JsonBackReference(value = "user-comment")
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name="user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;


    public FoodComment() {
    }


    public FoodComment(Integer id, String comment, Food food, User user) {
        this.id = id;
        this.comment = comment;
        this.food = food;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
