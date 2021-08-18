package com.rockieslearning.crud.websocket;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * Created by TanVOD on Aug, 2021
 */


@Getter
@Setter
@ToString
public class    MessageDto {
    String name;
    String message;
    List<String> urlImage;
    Date date;
}
