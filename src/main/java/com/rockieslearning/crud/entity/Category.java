package com.rockieslearning.crud.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by TanVOD on Jun, 2021
 */

@Entity
@Table(name = "CATEGORIES")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_seq")
    private Integer categoryId;

    @Column(name = "category_name")
    private String name;

    @Column(name = "category_description")
    private String description;

    @Column(name = "category_image")
    private String image;

    @JsonManagedReference(value = "category-food")
    @OneToMany(fetch = FetchType.EAGER , mappedBy = "category")
    private Set<Food> foods = new HashSet<Food>();


    public Category() {
    }

    public Category(Integer categoryId, String name, String description, String image, Set<Food> foods) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.image = image;
        this.foods = foods;
    }



    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Food> getFoods() {
        return foods;
    }

    public void setFoods(Set<Food> foods) {
        this.foods = foods;
    }
}
