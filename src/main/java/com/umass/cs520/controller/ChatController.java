package com.umass.cs520.controller;

import java.util.Map;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

  private final OpenAiChatClient chatClient;

  @Autowired
  public ChatController(OpenAiChatClient chatClient) {
    this.chatClient = chatClient;
  }

  @GetMapping("/ai/generate")
  public Map generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
    return Map.of("generation", chatClient.call(message));
  }

  @GetMapping("/ai/generateStream")
  public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
    Prompt prompt = new Prompt(new UserMessage(message));
    return chatClient.stream(prompt);
  }
}

