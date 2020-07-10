package com.rob.FastQuestion.service;

import com.rob.FastQuestion.exception.AnswerQuestionNullException;
import com.rob.FastQuestion.exception.ObjectIsNullException;
import com.rob.FastQuestion.exception.QuestionIsNotFoundException;
import com.rob.FastQuestion.models.Answer;
import com.rob.FastQuestion.models.Question;
import com.rob.FastQuestion.repo.AnswerRepo;
import com.rob.FastQuestion.repo.QuestionRepo;
import com.rob.FastQuestion.service.interfaces.IAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepo answerRepo;
    private final QuestionRepo questionRepo;

    public Answer saveAnswer(Answer answer) {
        if (answer != null) {
            if (answer.getQuestion() == null) {
                throw new AnswerQuestionNullException("Answer Have not Question: {} " + answer);
            }
            questionRepo.findById(answer.getQuestion().getId()).orElseThrow(() ->
                    new QuestionIsNotFoundException("Question is not found: {} " + answer.getQuestion()));
            return answerRepo.save(answer);
        }
        throw new ObjectIsNullException("Answer is null: {}");
    }

    public List<Answer> findAll() {
        return answerRepo.findAll();
    }

    public List<Answer> getAnswersByQuestion(Question question) {
        return answerRepo.findAnswersByQuestion(question);
    }


    //Question can have the same text, this method just for test
    public List<Answer> getAnswersByQuestionText(String text) {
        return answerRepo.findAnswersByQuestionText(text);
    }

    public List<Answer> getAnswersByQuestionId(Long id) {
        return answerRepo.findAnswersByQuestionId(id);
    }

    public Integer getCountOfAnswers() {
        return answerRepo.getCountOfQuestions();
    }
}
