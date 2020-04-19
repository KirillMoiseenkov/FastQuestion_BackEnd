package com.rob.FastQuestion.service.interfaces;

import com.rob.FastQuestion.models.Question;

import java.util.List;

public interface IQuestionService {
    Question saveQuestion(Question question);
    List<Question> findAll();
    Question getRandomQuestion();
}
