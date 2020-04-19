package com.rob.FastQuestion.service;

import com.rob.FastQuestion.exception.AnswerQuestionNullException;
import com.rob.FastQuestion.models.Answer;
import com.rob.FastQuestion.models.Question;
import com.rob.FastQuestion.repo.AnswerRepo;
import com.rob.FastQuestion.repo.QuestionRepo;
import com.rob.FastQuestion.service.interfaces.IAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AnswerService implements IAnswerService {

    private final AnswerRepo answerRepo;
    private final QuestionRepo questionRepo;

    @Override
    public Answer saveAnswer(Answer answer) {

        if (!Objects.isNull(answer) && !Objects.isNull(answer.getQuestion())) {
            if (questionRepo.findById(answer.getQuestion().getId()).get() != null)
                return answerRepo.save(answer);
        } else try {
            throw new AnswerQuestionNullException("Answer Have not Question: {} " + answer);
        } catch (AnswerQuestionNullException e) {
            e.printStackTrace();
        }
        return null;
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
