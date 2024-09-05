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

    @Autowired
    WebClient webClient;

    Gson gson = new Gson();

    public String postTest(RequestMessageDto requestMessageDto){
        String str = requestMessageDto.getContent();
        requestMessageDto.setContent(str+"카이스트 주변 관광지 추천해줘.");

        ResponseMessageDto responseMessageDto = webClient.post()
                .uri("/message")
                .bodyValue(requestMessageDto)
                .retrieve()
                .bodyToMono(ResponseMessageDto.class)
                .block();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("content", responseMessageDto.getContent());

        return gson.toJson(jsonObject);
    }
}
