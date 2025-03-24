package com.athiaa.AA2_web.Model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    // Configure WebClient bean
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
