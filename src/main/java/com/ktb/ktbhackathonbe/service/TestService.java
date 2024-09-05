package com.ktb.ktbhackathonbe.service;

import com.ktb.ktbhackathonbe.dto.RequestMessageDto;
import com.ktb.ktbhackathonbe.dto.ResponseMessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TestService {

    public String postTest(){
        WebClient webClient = WebClient.builder().build();
        String url = "http://10.178.0.2:7777/message/";

        RequestMessageDto requestMessageDto = new RequestMessageDto();
        requestMessageDto.setContent("당진 관광지 추천.");

        ResponseMessageDto responseMessageDto = webClient.post()
                .uri(url)
                .bodyValue(requestMessageDto)
                .retrieve()
                .bodyToMono(ResponseMessageDto.class)
                .block();

        return responseMessageDto.toString();
    }
}
