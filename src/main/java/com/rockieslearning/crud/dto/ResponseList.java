package com.rockieslearning.crud.dto;

/**
 * Created by TanVOD on Jul, 2021
 */
public class ResponseList {
    private int totalItems;
    private Object data;

    public ResponseList() {
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }


}
