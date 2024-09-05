package com.ktb.ktbhackathonbe.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ktb.ktbhackathonbe.dto.RequestMessageDto;
import com.ktb.ktbhackathonbe.dto.ResponseMessageDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TestService {

    Gson gson = new Gson();

    public String postTest(RequestMessageDto requestMessageDto){
        WebClient webClient = WebClient.builder().build();
        String url = "http://10.178.0.2:7777/message/";

        String str = requestMessageDto.getContent();
        requestMessageDto.setContent(str+"이랑 카이스트 주변 관광지 추천해줘.");

        ResponseMessageDto responseMessageDto = webClient.post()
                .uri(url)
                .bodyValue(requestMessageDto)
                .retrieve()
                .bodyToMono(ResponseMessageDto.class)
                .block();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("content", responseMessageDto.getContent());

        return gson.toJson(jsonObject);
    }
}
