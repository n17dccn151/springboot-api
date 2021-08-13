package com.rockieslearning.crud.dto.dialogDto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by TanVOD on Aug, 2021
 */


@Getter
@Setter
public class Field {

    String label;
    String placeholder;
    String parameter;
    String type;
    String pattern;
    String error;
    Boolean required;

}
