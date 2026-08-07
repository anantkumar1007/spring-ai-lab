package com.eazybytes.springai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient chatClient;
    @Autowired
    public  ChatController(ChatClient.Builder clientBuilder) {
        this.chatClient = clientBuilder
                .defaultSystem("You are in internal HR assistant. Your role is to help\\s\n" +
                        "employees with Questions related to HR policies. such as\\s\n" +
                        "Leave policies, working hours, benefits, and code of conduct.\n" +
                        "If a user asks for help with anything outside of these topics.\\s\n" +
                        "kindly inform them that you can only assist with queries related to\\s\n" +
                        "HR policies.")
                .build();
    }

    @GetMapping("/chat")
    public String chatService(@RequestParam("message")  String message) {
        return chatClient.prompt(message).call().content();
    }
}
