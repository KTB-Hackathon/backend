package com.ktb.ktbhackathonbe.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ktb.ktbhackathonbe.dto.RequestMessageDto;
import com.ktb.ktbhackathonbe.dto.ResponseMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TestService {

    @Autowired
    private RestTemplate restTemplate;

    Gson gson = new Gson();

    public String postTest(RequestMessageDto requestMessageDto){
        String url = "http://10.178.0.2:7777/message"; // URL 조합

        try {
            // RestTemplate을 사용하여 POST 요청 보내기
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestMessageDto, String.class);

            // ResponseEntity의 상태 코드 검사 및 바디 읽기
            if (response.getStatusCode().is2xxSuccessful()) {
                String responseBody = response.getBody();
                if (responseBody == null || responseBody.isEmpty()) {
                    JsonObject errorResponse = new JsonObject();
                    errorResponse.addProperty("content", "서버로부터 응답이 없습니다.");
                    return gson.toJson(errorResponse);
                }
                return responseBody;  // 정상적인 응답 반환
            } else {
                JsonObject errorResponse = new JsonObject();
                errorResponse.addProperty("content", "서버로부터 에러 응답: " + response.getStatusCode());
                return gson.toJson(errorResponse);
            }
        } catch (Exception e) {
            JsonObject errorResponse = new JsonObject();
            errorResponse.addProperty("content", "요청 처리 중 오류가 발생했습니다: " + e.getMessage());
            return gson.toJson(errorResponse);
        }
    }
}
