package com.rob.FastQuestion.service.interfaces;

import com.rob.FastQuestion.models.Answer;
import com.rob.FastQuestion.models.Question;

import java.util.List;

public interface IAnswerService {
    Answer saveAnswer(Answer answer);

    List<Answer> findAll();

    List<Answer> getAnswersByQuestion(Question question);

    List<Answer> getAnswersByQuestionText(String text);

    List<Answer> getAnswersByQuestionId(Integer id);

}
