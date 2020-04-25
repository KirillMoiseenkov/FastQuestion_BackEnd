package com.rob.FastQuestion.service.utils;

import com.rob.FastQuestion.models.Question;
import com.rob.FastQuestion.models.dto.QuestionDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionUtils {
    public static QuestionDTO mapQuestionToQuestionDTO(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setAnswers(question.getAnswers());
        questionDTO.setLang(question.getLang());
        questionDTO.setText(question.getText());
        return questionDTO;
    }

    public static List<QuestionDTO> mapQuestionToQuestionDTO(List<Question> questionList) {
        if (questionList != null && !questionList.isEmpty())
            questionList.stream().map(question -> {
                QuestionDTO questionDTO = new QuestionDTO();
                questionDTO.setId(question.getId());
                questionDTO.setAnswers(question.getAnswers());
                questionDTO.setLang(question.getLang());
                questionDTO.setText(question.getText());
                return questionDTO;
            });
        return new ArrayList<>();
    }

}
