package com.rockieslearning.crud.ggdialog;

import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by TanVOD on Aug, 2021
 */
@Configuration
public class DialogFlowConfiguration {

    @Bean
    public JacksonFactory jacksonFactory() {
        return JacksonFactory.getDefaultInstance();
    }
}
