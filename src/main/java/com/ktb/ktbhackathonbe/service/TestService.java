package com.ktb.ktbhackathonbe.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ktb.ktbhackathonbe.dto.RequestMessageDto;
import com.ktb.ktbhackathonbe.dto.ResponseMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TestService {

//    @Autowired
//    WebClient webClient;

    Gson gson = new Gson();

    public String postTest(RequestMessageDto requestMessageDto){
        WebClient client = WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
                .baseUrl("http://10.178.0.2:7777")
                .build();

        String response = client.post()
                .uri("/message")
                .bodyValue(requestMessageDto)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        //응답이 없을 경우
        if (response == null) {
            JsonObject errorResponse = new JsonObject();
            errorResponse.addProperty("content", "서버 응답이 없습니다.");
            return gson.toJson(errorResponse);
        }

//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("content", responseMessageDto.getContent());

        return response;
    }
}
