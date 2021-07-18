package com.rockieslearning.crud.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by TanVOD on Jul, 2021
 */

@Getter
@Setter
public class FileInfo {
    private String name;
    private String url;

    public FileInfo(String name, String url) {
        this.name = name;
        this.url = url;
    }

}
