package com.rockieslearning.crud.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.dialogflow.v2beta1.model.*;
import com.rockieslearning.crud.dto.CategoryDto;
import com.rockieslearning.crud.dto.dialogDto.*;
import com.rockieslearning.crud.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.api.client.json.JsonGenerator;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

/**
 * Created by TanVOD on Aug, 2021
 */
@RestController
public class DialogFlowWebhookController {

    @Autowired
    private final JacksonFactory jacksonFactory;

    @Autowired
    CategoryService categoryService;

    public DialogFlowWebhookController(JacksonFactory jacksonFactory) {
        this.jacksonFactory = jacksonFactory;
    }

    @PostMapping(value = "/dialogflow-webhook", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String webhook(@RequestBody String rawData) throws IOException {
        //Step 1. Parse the request
        GoogleCloudDialogflowV2WebhookRequest request = jacksonFactory
                .createJsonParser(rawData)
                .parse(GoogleCloudDialogflowV2WebhookRequest.class);

        //Step 2. Process the request

        System.out.println("--------"+ request.getQueryResult().getFulfillmentMessages());
        System.out.println("--------"+ request.getQueryResult().getParameters());
        System.out.println("--------"+ request.getQueryResult().getWebhookPayload());
        System.out.println("--------"+ request.getQueryResult().getFulfillmentText());
        
        String displayName = request.getQueryResult().getIntent().getDisplayName();
        GoogleCloudDialogflowV2IntentMessage msg = new GoogleCloudDialogflowV2IntentMessage();
        GoogleCloudDialogflowV2IntentMessageText text = new GoogleCloudDialogflowV2IntentMessageText();
        List<GoogleCloudDialogflowV2IntentMessageCard> cards = new ArrayList<>();
        GoogleCloudDialogflowV2IntentMessageCard card = new GoogleCloudDialogflowV2IntentMessageCard();
        List<GoogleCloudDialogflowV2IntentMessage> messages = new ArrayList<>();

        GoogleCloudDialogflowV2IntentMessageCardButton cardButton = new GoogleCloudDialogflowV2IntentMessageCardButton();
        switch (displayName){
            case "list_available_meal_categoryy":

                List<CategoryDto> categoryDtoList  = categoryService.retrieveCategories();
                text.setText(Arrays.asList("Hiện tại chúng tôi có "+ categoryDtoList.size() + " loại bạn muốn tìm loại nào?"));
                messages.add(new GoogleCloudDialogflowV2IntentMessage().setText(text));
                categoryDtoList.forEach(item ->{
                    card.setImageUri(item.getImage());
                    card.setSubtitle(item.getDescription());
                    card.setTitle(item.getName());
                    cardButton.setText("Xem");
                    cardButton.setPostback("google.com");
                    card.setButtons(Arrays.asList(cardButton));
                    msg.setCard(card);

                    messages.add(msg);

                });


        }

        //Step 3. Build the response message

//        text.setText(Arrays.asList("Welcome to Spring Boot"));
//        msg.setText(text);


//        text.setText(Arrays.asList(request.getQueryResult().getFulfillmentText()));
//        System.out.println(Arrays.asList(request.getQueryResult().getFulfillmentText()));
//        msg.setText(text);










        ///
        Map<String, Object> map = new HashMap<>();
        BotCopy botCopy = new BotCopy();


        Link link = new Link();
        List<Suggestion> suggestions = new ArrayList<>();
        Action action = new Action();
        Suggestion suggestion = new Suggestion();



        link.setTarget("_blank");
        link.setUrl("https://botcopy.com");
        action.setLink(link);

        suggestion.setAction(action);
        suggestion.setTitle("test");
        suggestions.add(suggestion);


        botCopy.setSuggestions(suggestions);


        ResponseBotCopy responseBotCopy = new ResponseBotCopy();
        responseBotCopy.setBotcopy(Arrays.asList(botCopy));
        ObjectMapper oMapper = new ObjectMapper();

        map = oMapper.convertValue(responseBotCopy, Map.class);

        messages.add(new GoogleCloudDialogflowV2IntentMessage().setPayload(map));




        GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();
        response.setFulfillmentMessages(messages);
        ///


        StringWriter stringWriter = new StringWriter();
        JsonGenerator jsonGenerator = jacksonFactory.createJsonGenerator(stringWriter);
        jsonGenerator.enablePrettyPrint();
        jsonGenerator.serialize(response);
        jsonGenerator.flush();
        return stringWriter.toString();
    }
}
