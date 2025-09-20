package org.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.config.GroqConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class GroqChatService {
   private final WebClient webClient;
   public GroqChatService(GroqConfig config){
       this.webClient= WebClient.builder()
               .baseUrl(config.getUrl())
               .defaultHeader("Authorization","Bearer "+config.getKey())
               .build();

   }
    public Mono<String> chat(List<Map<String, String>> messages) {


        Map<String, Object> requestBody = Map.of(
                "model", "llama-3.3-70b-versatile",
                "messages", messages
        );

        return webClient.post()
                .bodyValue(requestBody)  // Pass the Map directly
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(json -> json.get("choices").get(0).get("message").get("content").asText());
    }

}
