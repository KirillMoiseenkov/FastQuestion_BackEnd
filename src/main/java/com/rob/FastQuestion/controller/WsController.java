package com.rob.FastQuestion.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {
    @MessageMapping("/user")
    @SendTo("/topic/user")
    public String broadcastNews(@Payload String message) {
        message = "you";
        me
        return message;
    }
}
