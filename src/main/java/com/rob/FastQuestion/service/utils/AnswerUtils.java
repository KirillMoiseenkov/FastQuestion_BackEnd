package com.rob.FastQuestion.service.utils;

import com.rob.FastQuestion.models.Answer;
import com.rob.FastQuestion.models.dto.AnswerDTO;

public class AnswerUtils {
    public static Answer AnswerDTOtoAnswer(AnswerDTO answerDTO) {
        Answer answer = new Answer();
        answer.setText(answerDTO.getText());
        answer.setId(answerDTO.getId());
        answer.setQuestion(answerDTO.getQuestion());
        return answer;
    }

}
