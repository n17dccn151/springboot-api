package com.rockieslearning.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * Created by TanVOD on Jun, 2021
 */
@Entity
@Table(name = "FOOD_IMAGES")
public class FoodImage {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_images_seq")
    private Integer id;

    @Column(name = "image")
    private String image;


    @JsonBackReference(value = "food-image")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Food food;

    public FoodImage() {
    }

    public FoodImage(Integer id, String image, Food food) {
        this.id = id;
        this.image = image;
        this.food = food;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
