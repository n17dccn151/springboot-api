package com.rockieslearning.crud.dto.dialogDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by TanVOD on Aug, 2021
 */

@Getter
@Setter
public class BotCopy {
    List<Suggestion> suggestions;
    Card card;
//    String text;
    List<Card> carousel;
    Form form;
    ListB list;
    Text text;
}
