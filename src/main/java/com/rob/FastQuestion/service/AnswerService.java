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

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService implements IAnswerService {

    private final AnswerRepo answerRepo;
    private final QuestionRepo questionRepo;

    @Override
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


    @Override
    public List<Answer> findAll() {
        return answerRepo.findAll();
    }

    @Override
    public List<Answer> getAnswersByQuestion(Question question) {
        return answerRepo.findAnswersByQuestion(question);
    }


    //Question can have the same text, this method just for test
    @Override
    public List<Answer> getAnswersByQuestionText(String text) {
        return answerRepo.findAnswersByQuestionText(text);
    }

    @Override
    public List<Answer> getAnswersByQuestionId(Integer id) {
        return answerRepo.findAnswersByQuestionId(id);
    }
}
