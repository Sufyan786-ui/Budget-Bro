package com.restapi.ExpenseProject.openai;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class Service {

    @Value("${openrouter.api.url}")
    private String OPENROUTER_API_URL;

    @Value("${openrouter.api.key}")
    private String OPENROUTER_API_KEY;

    public String callgpt(String userprompt) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(OPENROUTER_API_KEY);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "mistralai/mistral-7b-instruct:free");

        List<Map<String, String>> messages = List.of(
                Map.of("role", "system", "content", "You are a financial assistant for expense tracking."),
                Map.of("role", "user", "content", userprompt)
        );
        requestBody.put("messages", messages);

        HttpEntity<String> entity = new HttpEntity<>(new ObjectMapper().writeValueAsString(requestBody), headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(OPENROUTER_API_URL, entity, String.class);
            String json = response.getBody();

            // Parse JSON and extract assistant message
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(json, Map.class);

            List<Map<String, Object>> choices = (List<Map<String, Object>>) map.get("choices");
            Map<String, Object> firstChoice = choices.get(0);
            Map<String, Object> message = (Map<String, Object>) firstChoice.get("message");
            String content = (String) message.get("content");

            return content;

        } catch (Exception e) {
            System.out.println("❌ Error calling OpenRouter API: " + e.getMessage());
            throw e;
        }
    }

}


