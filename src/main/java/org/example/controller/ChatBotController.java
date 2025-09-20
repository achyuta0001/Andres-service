package org.example.controller;

import org.example.service.base.GroqChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.example.service.impl.GroqChatServiceImpl;
import reactor.core.publisher.Mono;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatBotController {
    private final GroqChatService chatService;
    List<Map<String, String>> messages = new ArrayList<>();

    public ChatBotController(GroqChatServiceImpl chatService){
        this.chatService=chatService;
    }
    @GetMapping
    public Mono<String> chat( @RequestParam String q)throws IOException{

        messages.add(Map.of("role","system", "content", "your name is Andres.when greeted introduce your name along with your greeting"));

        messages.add(Map.of("role", "user", "content", q));
        return chatService.chat(messages);
    }
    @GetMapping(value = "/clearHistory")
    public void clearHistory()
    {
        messages.clear();
    }
}
