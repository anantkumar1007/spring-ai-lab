package com.eazybytes.multimodel.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
 @RequestMapping("/api")

public class MultiModelController {

    private final ChatClient openAichatClient;
    private final ChatClient ollamaChatClient;


    MultiModelController(@Qualifier("openAiChatClient") ChatClient  openAicChatClient,
                         @Qualifier("ollamaChatClient") ChatClient ollamaChatClient) {
        this.openAichatClient = openAicChatClient;
        this.ollamaChatClient = ollamaChatClient;
    }

    @RequestMapping("/ollama/chat")
    public String ollamaChatClient(@RequestParam("message") String message){
        return ollamaChatClient.prompt(message).call().content();
    }

    @RequestMapping("/openai/chat")
    public String openAiChatClient(@RequestParam("message") String message){
        return openAichatClient.prompt(message).call().content();
    }
}
