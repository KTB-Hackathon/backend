package com.ktb.ktbhackathonbe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("http://10.178.0.2:7777")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}
