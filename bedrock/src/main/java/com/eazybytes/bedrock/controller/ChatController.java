package com.eazybytes.bedrock.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClentBuilder){
        this.chatClient = chatClentBuilder.build();
    }
    @RequestMapping("/chat")
    public String chat(@RequestParam("message") String message){

        return chatClient.prompt(message).call().content();
    }
}
