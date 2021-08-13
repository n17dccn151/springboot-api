package com.rockieslearning.crud.dto.dialogDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by TanVOD on Aug, 2021
 */
@Getter
@Setter
public class Form {
    String style;
    String title;
    String subtitle;
    Boolean force;
    List<Field> fields;
    Action action;

}
