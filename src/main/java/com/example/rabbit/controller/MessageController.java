package com.example.rabbit.controller;

import com.example.rabbit.dto.MessageIn;
import com.example.rabbit.service.MessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @PostMapping("/putmessage")
    public void sendMessage(@RequestBody MessageIn messageIn) {
        service.sendMessage(messageIn);
    }
}
