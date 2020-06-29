package com.rob.FastQuestion.service.utils;

import com.rob.FastQuestion.models.Question;
import com.rob.FastQuestion.models.QuestionFile;
import com.rob.FastQuestion.models.dto.QuestionDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionUtils {
    public static QuestionDTO mapQuestionToQuestionDTO(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setAnswers(question.getAnswers());
        questionDTO.setLang(question.getLang());
        questionDTO.setText(question.getText());
        questionDTO.setQuestionType(question.getQuestionType());

        return questionDTO;
    }

    public static QuestionDTO mapQuestionToQuestionDTO(Question question, List<QuestionFile> questionFileList) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setAnswers(question.getAnswers());
        questionDTO.setLang(question.getLang());
        questionDTO.setText(question.getText());


        question.getQuestionFiles().forEach(questionFile -> {
            questionDTO.getFileIds().put(questionFile.getId(), questionFile.getCount());
        });


        questionDTO.setQuestionType(question.getQuestionType());

        return questionDTO;
    }

    public static List<QuestionDTO> mapQuestionToQuestionDTO(List<Question> questionList) {
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        if (questionList != null && !questionList.isEmpty()) {
            questionDTOS = questionList.stream().map(question -> {
                QuestionDTO questionDTO = new QuestionDTO();
                questionDTO.setId(question.getId());
                questionDTO.setAnswers(question.getAnswers());
                questionDTO.setLang(question.getLang());
                questionDTO.setText(question.getText());
                questionDTO.setAnswers(question.getAnswers());
                return questionDTO;
            }).collect(Collectors.toList());
        }
        return questionDTOS;
    }

}
