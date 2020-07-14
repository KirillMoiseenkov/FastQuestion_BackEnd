package com.rob.FastQuestion.service;

import com.rob.FastQuestion.models.dto.AnswerDTO;
import com.rob.FastQuestion.models.dto.QuestionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@Slf4j
public class WsServiceImpl {
    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    public void addToRoom(Long roomId) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(roomId);
        messagingTemplate.convertAndSend(format("/topic/user/%s", roomId), questionDTO);
    }

    public void sendMessage(Long roomId, AnswerDTO answerDTO) {
        messagingTemplate.convertAndSend(format("/topic/user/%s", roomId), answerDTO);
    }

}
