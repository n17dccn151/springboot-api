package com.rockieslearning.crud.entity;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by TanVOD on Jun, 2021
 */

@Entity
@Table(name = "FOODS")
public class Food {

    @Id
    @GeneratedValue
    private Integer foodId;

    @Column(name = "food_name")
    private String name;

    @Column(name = "food_price")
    private Double price;

    @Column(name = "food_description")
    private String description;

    @Column(name = "food_total_rating")
    private Float rating;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name="category_id")
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


    public Food() {
    }

    public Food(Integer foodId, String name, Double price, String description, Float rating, Category category, Date createdDate, Date updatedDate) {
        this.foodId = foodId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.category = category;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
    public Food(String name, Double price, String description, Float rating, Category category) {
        this.foodId = foodId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.category = category;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }



    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
