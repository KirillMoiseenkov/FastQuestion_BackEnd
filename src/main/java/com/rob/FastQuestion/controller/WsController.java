package com.rob.FastQuestion.controller;

import com.rob.FastQuestion.models.Answer;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {
    @MessageMapping("/user")
    @SendTo("/topic/user")
    public Answer broadcastNews(@Payload Answer answer) {
        Answer answer1 = new Answer();
        answer1.setText(answer.getText() + " ku ku");
        return answer1;
    }
}
