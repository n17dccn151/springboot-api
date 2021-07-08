package com.rockieslearning.crud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.rockieslearning.crud.entity.FoodImage;
import com.rockieslearning.crud.entity.FoodRating;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TanVOD on Jul, 2021
 */
public class FoodRatingDto {

    @JsonView(View.FoodWithImageComment.class)
    private Integer id;

    @JsonView(View.FoodWithImageComment.class)
    private String comment;

    @JsonView(View.FoodWithImageComment.class)
    private Integer rating;

    @JsonView(View.FoodWithImageComment.class)
    private String userName;

    public FoodRatingDto toDto(FoodRating entity) {
        FoodRatingDto dto = new FoodRatingDto();
        dto.setId(entity.getId());
        dto.setComment(entity.getComment());
        dto.setRating(entity.getRate());
        dto.setUserName(entity.getUser().getPhone());
//        dto.setFood_id(entity.getFood().getFoodId());
        return dto;
    }


    public List<FoodRatingDto> toListDto(List<FoodRating> listEntity) {
        List<FoodRatingDto> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.toDto(e));
        });

        return listDto;
    }

    public List<FoodRating> toListEntity(List<FoodRatingDto> listDto) {
        List<FoodRating> listEti = new ArrayList<>();

        listDto.forEach(e->{
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


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName.substring(0,7).concat("xxx");
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
