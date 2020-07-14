package com.rob.FastQuestion.service;

import com.rob.FastQuestion.models.Answer;
import com.rob.FastQuestion.models.Question;
import com.rob.FastQuestion.models.dto.AnswerDTO;
import com.rob.FastQuestion.models.dto.QuestionDTO;
import com.rob.FastQuestion.service.utils.AnswerUtils;
import com.rob.FastQuestion.service.utils.QuestionUtils;
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

    public void addQuestion(Long roomId, QuestionDTO questionDTO) {
        questionService.saveQuestion(QuestionUtils.mapQuestionDTOtoQuestionForSaving(questionDTO));
        messagingTemplate.convertAndSend(format("/topic/user/%s", roomId), questionDTO);
    }

    public void sendMessage(Long roomId, AnswerDTO answerDTO) {
        Question question = new Question();
        question.setId(roomId);
        answerDTO.setQuestion(question);
        Answer answer = AnswerUtils.AnswerDTOtoAnswer(answerDTO);
        answerService.saveAnswer(answer);
        messagingTemplate.convertAndSend(format("/topic/user/%s", roomId), answer);
    }

}
