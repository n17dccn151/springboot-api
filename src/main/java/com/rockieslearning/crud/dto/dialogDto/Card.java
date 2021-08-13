package com.rockieslearning.crud.dto.dialogDto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by TanVOD on Aug, 2021
 */
@Getter
@Setter
public class Card {
    Action action;
    String body;
    Image image;
    String subtitle;
    String title;

}
