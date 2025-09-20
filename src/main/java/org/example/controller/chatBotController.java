package org.example.controller;

import org.example.service.base.groqChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.example.service.impl.groqChatServiceImpl;
import reactor.core.publisher.Mono;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class chatBotController {
    private final groqChatService chatService;
    List<Map<String, String>> messages = new ArrayList<>();

    public chatBotController(groqChatServiceImpl chatService){
        this.chatService=chatService;
    }
    @GetMapping
    public Mono<String> chat( @RequestParam String q)throws IOException{

        //messages.add(Map.of("role","system", "content", "your name is jarvis.if user sends a statement instead of question ,he may ask question about the statement in future."));

        messages.add(Map.of("role", "user", "content", q));
        return chatService.chat(messages);
    }
    @GetMapping(value = "/clearHistory")
    public void clearHistory()
    {
        messages.clear();
    }
}
