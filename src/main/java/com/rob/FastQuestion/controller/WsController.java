package com.rob.FastQuestion.controller;

import com.rob.FastQuestion.models.dto.AnswerDTO;
import com.rob.FastQuestion.models.dto.QuestionDTO;
import com.rob.FastQuestion.service.WsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {
    @Autowired
    WsServiceImpl wsService;

    @MessageMapping("/user/send-answer/{id}")
    public void sendAnswer(@Payload AnswerDTO answerDTO, @DestinationVariable Long id) {
        wsService.sendMessage(id, answerDTO);
    }

    @MessageMapping("/user/{id}")
    public void addToRoom(@Payload QuestionDTO questionDTO, @DestinationVariable Long id) {
        wsService.addQuestion(id, questionDTO);
    }
}
