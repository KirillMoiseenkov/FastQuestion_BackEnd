package com.rob.FastQuestion.controller;

import com.rob.FastQuestion.models.Answer;
import com.rob.FastQuestion.models.dto.AnswerDTO;
import com.rob.FastQuestion.models.dto.WsMessage;
import com.rob.FastQuestion.service.AnswerService;
import com.rob.FastQuestion.service.WsServiceImpl;
import com.sun.xml.internal.ws.api.WSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {
    @Autowired
    WsServiceImpl wsService;

    @MessageMapping("/user/{id}")
    public void addToRoom(@DestinationVariable Long id) {
        wsService.addToRoom(id);
    }

    @MessageMapping("/user/send-answer/{id}")
    public void sendAnswer(@Payload AnswerDTO answerDTO, @DestinationVariable Long id) {
        wsService.sendMessage(id, answerDTO);
    }
}
