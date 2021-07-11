package com.rockieslearning.crud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.rockieslearning.crud.entity.FoodImage;
import com.rockieslearning.crud.entity.FoodRating;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TanVOD on Jul, 2021
 */
public class FoodRatingDto {

    @JsonView(View.FoodWithImageComment.class)
    private Integer id;

    @JsonView(View.FoodWithImageComment.class)
    @NotNull(message = "foodId cannot be null")
    private Integer foodId;

    @JsonView(View.FoodWithImageComment.class)
    private Long userId;

    @JsonView(View.FoodWithImageComment.class)
    private String comment;

    @JsonView(View.FoodWithImageComment.class)
    @Min(value = 1, message = "rating should not be less than 1")
    @Max(value = 5, message = "rating should not be greater than 5")
    private Integer rating;

    @JsonView(View.FoodWithImageComment.class)
    private String userName;

    public FoodRatingDto toDto(FoodRating entity) {
        FoodRatingDto dto = new FoodRatingDto();
        dto.setId(entity.getId());
        dto.setComment(entity.getComment());
        dto.setRating(entity.getRate());
        dto.setUserName(entity.getUser().getPhone());
        dto.setFoodId(entity.getFood().getFoodId());
        dto.setUserId(entity.getUser().getUserId());

//        dto.setFood_id(entity.getFood().getFoodId());
        return dto;
    }


    public List<FoodRatingDto> toListDto(List<FoodRating> listEntity) {
        List<FoodRatingDto> listDto = new ArrayList<>();

        listEntity.forEach(e -> {
            listDto.add(this.toDto(e));
        });

        return listDto;
    }

    public List<FoodRating> toListEntity(List<FoodRatingDto> listDto) {
        List<FoodRating> listEti = new ArrayList<>();

        listDto.forEach(e -> {
            listEti.add(this.toEntity(e));
        });
        return listEti;
    }


    public FoodRating toEntity(FoodRatingDto dto) {
        FoodRating entity = new FoodRating();
        //entity.setId(dto.getId());
        entity.setComment(dto.getComment());
        entity.setRate(dto.getRating());
        //entity.setFood(new FoodDto().toEntity(foodDto));


        return entity;
    }


    public FoodRatingDto(Integer id, Integer foodId, Long userId, String comment, Integer rating, String userName) {
        this.id = id;
        this.foodId = foodId;
        this.userId = userId;
        this.comment = comment;
        this.rating = rating;
        this.userName = userName;
    }

    public FoodRatingDto() {
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName.substring(0, 7).concat("xxx");
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
