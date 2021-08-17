package com.rockieslearning.crud.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.dialogflow.v2beta1.model.*;
import com.rockieslearning.crud.dto.CategoryDto;
import com.rockieslearning.crud.dto.FoodDto;
import com.rockieslearning.crud.dto.dialogDto.*;
import com.rockieslearning.crud.service.CategoryService;
import com.rockieslearning.crud.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.api.client.json.JsonGenerator;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    FoodService foodService;

    public DialogFlowWebhookController(JacksonFactory jacksonFactory) {
        this.jacksonFactory = jacksonFactory;
    }

    @PostMapping(value = "/dialogflow-webhook", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String webhook(@RequestBody String rawData, HttpServletRequest rq) throws IOException {


        Long userId = (Long) rq.getAttribute("userId");


        //Step 1. Parse the request
        GoogleCloudDialogflowV2WebhookRequest request = jacksonFactory
                .createJsonParser(rawData)
                .parse(GoogleCloudDialogflowV2WebhookRequest.class);

        //Step 2. Process the request

        System.out.println("---rawData-----"+ rawData);
        System.out.println("---getFulfillmentMessages()-----"+ request.getQueryResult().getFulfillmentMessages());
        System.out.println("---getParameters()-----"+ request.getQueryResult().getParameters());
        System.out.println("---getWebhookPayload()-----"+ request.getQueryResult().getWebhookPayload());
        System.out.println("---getFulfillmentText()-----"+ request.getQueryResult().getFulfillmentText());


        System.out.println("---Payload-----"+ request.getOriginalDetectIntentRequest().getPayload());
        System.out.println("---getQueryText()---"+ request.getQueryResult().getQueryText());

        String displayName = request.getQueryResult().getIntent().getDisplayName();
        GoogleCloudDialogflowV2IntentMessage msg = new GoogleCloudDialogflowV2IntentMessage();
        GoogleCloudDialogflowV2IntentMessageText text = new GoogleCloudDialogflowV2IntentMessageText();
        List<GoogleCloudDialogflowV2IntentMessageCard> cards = new ArrayList<>();
        GoogleCloudDialogflowV2IntentMessageCard card = new GoogleCloudDialogflowV2IntentMessageCard();
        List<GoogleCloudDialogflowV2IntentMessage> messages = new ArrayList<>();


        Map<String, Object> map = new HashMap<>();
        ObjectMapper oMapper = new ObjectMapper();
        BotCopy botCopy = new BotCopy();
        List<BotCopy> botCopies = new ArrayList<>();

        GoogleCloudDialogflowV2IntentMessageCardButton cardButton = new GoogleCloudDialogflowV2IntentMessageCardButton();
        switch (displayName){
            case "list_available_food":

                List<FoodDto> foodDtos  = foodService.retrieveFoods();
                text.setText(Arrays.asList("Hiện tại chúng tôi có "+ foodDtos.size() + " loại bạn muốn tìm loại nào?"));
                messages.add(new GoogleCloudDialogflowV2IntentMessage().setText(text));
                foodDtos.forEach(item ->{
                    card.setImageUri(item.getImages().get(0).getUrl());
                    card.setSubtitle(item.getDescription());
                    card.setTitle(item.getName());
                    cardButton.setText("Thêm vào giỏ hàng");
                    cardButton.setPostback("/cart/"+item.getFoodId()+"?qty="+ 1);
                    card.setButtons(Arrays.asList(cardButton));
                    msg.setCard(card);

                    messages.add(msg);

                });
            case "request_food":


                System.out.println("---xxxxxxxxxxgetParameters()-----"+ (ArrayList)request.getQueryResult().getParameters().get("food"));


                List<Card> items = new ArrayList<>();


                ((ArrayList)request.getQueryResult().getParameters().get("food")).forEach(item ->{
                    FoodDto foodsRequest = foodService.getAllFoodByName(item.toString().toLowerCase()).get(0);


                    Link link = new Link();
                    link.setTarget("_blank");
                    link.setUrl("/products/"+ foodsRequest.getFoodId());

                    Action action = new Action();
                    action.setLink(link);

                    Image image = new Image();
                    image.setUrl(foodsRequest.getImages().get(0).getUrl());


                    Card card1 = new Card();
                    card1.setAction(action);
                    card1.setBody("gia tien");
                    card1.setImage(image);
                    card1.setTitle(foodsRequest.getName());



                    items.add(card1);

                });
                botCopy.setCarousel(items);
                BotCopy botCopy1 = new BotCopy();
                Text text1 = new Text();
                text1.setDisplayText("Bạn muốn đặt hàng không ?");
                botCopy1.setText(text1);


                botCopies.add(botCopy);
                botCopies.add(botCopy1);

            case "request_info_food":



                List<Card> carousel = new ArrayList<>();


                System.out.println("---++++getParameters()-----"+ request.getQueryResult().getParameters().get("food").toString().toLowerCase());
                List<FoodDto> foods = foodService.getAllFoodByName(request.getQueryResult().getParameters().get("food").toString().toLowerCase());
                System.out.println("______sizeeeeeeeeee__"+foodService.getAllFoodByName(request.getQueryResult().getParameters().get("food").toString().toLowerCase()).size());

                foods.forEach(item ->{
                    Link link = new Link();
                    link.setTarget("_blank");
                    link.setUrl("/products/"+ item.getFoodId());

                    Action action = new Action();
                    action.setLink(link);

                    Suggestion buttonB = new Suggestion();
                    action.setButtons(Arrays.asList(buttonB));
                    buttonB.setTitle("Xem chi tiết");

                    Card card1 = new Card();
                    card1.setAction(action);
                    card1.setTitle(item.getName());
                    card1.setSubtitle(item.getPrice()+" đ");

                    Image image = new Image();
//                    image.setUrl("https://firebasestorage.googleapis.com/v0/b/nas-app-77.appspot.com/o/7d4b1a97-ef84-404a-93b4-0b4e1d812086jpg?alt=media");
                    image.setUrl(item.getImages().get(0).getUrl());
                    card1.setImage(image);

                    carousel.add(card1);

                });
                botCopy.setCarousel(carousel);
                botCopies.add(botCopy);

            case "request_food.request_food-yes":


//                List<BotCopy> botCopies = new ArrayList<>();
//                List<Card> carousel = new ArrayList<>();
//
//
//                System.out.println("---++++getParameters()-----"+ request.getQueryResult().getParameters().get("food").toString().toLowerCase());
//                List<FoodDto> foods = foodService.getAllFoodByName(request.getQueryResult().getParameters().get("food").toString().toLowerCase());
//                System.out.println("______sizeeeeeeeeee__"+foodService.getAllFoodByName(request.getQueryResult().getParameters().get("food").toString().toLowerCase()).size());
//
//                foods.forEach(item ->{
//                    Link link = new Link();
//                    link.setTarget("_blank");
//                    link.setUrl("/products/"+ item.getFoodId());
//
//                    Action action = new Action();
//                    action.setLink(link);
//
//                    Suggestion buttonB = new Suggestion();
//                    action.setButtons(Arrays.asList(buttonB));
//                    buttonB.setTitle("Xem chi tiết");
//
//                    Card card1 = new Card();
//                    card1.setAction(action);
//                    card1.setTitle(item.getName());
//                    card1.setSubtitle(item.getPrice()+" đ");
//
//                    Image image = new Image();
//                    image.setUrl(item.getImages().get(0).getUrl());
//                    card1.setImage(image);
//
//                    carousel.add(card1);
//
//                });
//
//                botCopy.setCarousel(carousel);


        }





//        Link link = new Link();
//        List<Suggestion> suggestions = new ArrayList<>();
//        Action action = new Action();
//        Suggestion suggestion = new Suggestion();
//
//
//
//        link.setTarget("_blank");
//        link.setUrl("https://botcopy.com");
//        action.setLink(link);
//
//        suggestion.setAction(action);
//        suggestion.setTitle("---"+userId);
//        suggestions.add(suggestion);





        ResponseBotCopy responseBotCopy = new ResponseBotCopy();
        responseBotCopy.setBotcopy(botCopies);




        map = oMapper.convertValue(responseBotCopy, Map.class);
        messages.add(new GoogleCloudDialogflowV2IntentMessage().setPayload(map));
        GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();
        response.setFulfillmentMessages(messages);


        StringWriter stringWriter = new StringWriter();
        JsonGenerator jsonGenerator = jacksonFactory.createJsonGenerator(stringWriter);
        jsonGenerator.enablePrettyPrint();
        jsonGenerator.serialize(response);
        jsonGenerator.flush();
        return stringWriter.toString();
    }
}
