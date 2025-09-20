package org.example.service.base;

import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface GroqChatService {
    public Mono<String> chat(List<Map<String, String>> messages);
}
