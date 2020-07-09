package com.rob.FastQuestion.controller;

import com.rob.FastQuestion.models.Answer;
import com.rob.FastQuestion.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {

    @Autowired
    AnswerService answerService;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/user/{id}")
    //@SendTo("/topic/user")
    public void saveAnswer(@Payload Answer answer, @DestinationVariable String id) {
        answer.setText("saved " + id);
        messagingTemplate.convertAndSend("/topic/user", answer);
    }

    @MessageMapping("/user/add-user/{id}")
    public void addUser(@Payload Answer answer, @DestinationVariable String id) {
        messagingTemplate.convertAndSend("/topic/user/" + id, answer);
    }
}
