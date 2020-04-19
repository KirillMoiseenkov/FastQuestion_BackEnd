package com.rob.FastQuestion.service;

import com.rob.FastQuestion.models.Question;
import com.rob.FastQuestion.repo.QuestionRepo;
import com.rob.FastQuestion.service.interfaces.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {

    private final QuestionRepo questionRepo;

    @Override
    public Question saveQuestion(Question question) {
        return questionRepo.save(question);
    }

    @Override
    public List<Question> findAll() {
        return questionRepo.findAll();
    }

    @Override
    public Question getRandomQuestion() {
        return questionRepo.getRandomQuestion();
    }
}
