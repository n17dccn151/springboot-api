package com.rockieslearning.crud.controller;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.dialogflow.v2beta1.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Created by TanVOD on Aug, 2021
 */
@RestController
public class DialogFlowWebhookController {

    @Autowired
    private final JacksonFactory jacksonFactory;

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
        //Step 3. Build the response message
        GoogleCloudDialogflowV2IntentMessage msg = new GoogleCloudDialogflowV2IntentMessage();
        GoogleCloudDialogflowV2IntentMessageText text = new GoogleCloudDialogflowV2IntentMessageText();
        text.setText(Arrays.asList("Welcome to Spring Boot"));
        msg.setText(text);

        GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();
        response.setFulfillmentMessages(Arrays.asList(msg));
        StringWriter stringWriter = new StringWriter();
        JsonGenerator jsonGenerator = jacksonFactory.createJsonGenerator(stringWriter);
        jsonGenerator.enablePrettyPrint();
        jsonGenerator.serialize(response);
        jsonGenerator.flush();
        return stringWriter.toString();
    }
}
