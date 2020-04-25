package com.rob.FastQuestion.service;

import com.rob.FastQuestion.exception.QuestionIsNotFoundException;
import com.rob.FastQuestion.models.Question;
import com.rob.FastQuestion.models.QuestionFile;
import com.rob.FastQuestion.repo.QuestionRepo;
import com.rob.FastQuestion.service.interfaces.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {

    private final QuestionRepo questionRepo;
    private final QuestionFileStorageService questionFileStorageService;

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

    public Question saveQuestionWithFile(MultipartFile multipartFile, Question question) {
        QuestionFile questionFile = questionFileStorageService.storageFile(multipartFile);
        question.setQuestionFile(questionFile);
        return questionRepo.save(question);
    }

    @Override
    public Question findById(Integer id) {
        return questionRepo.findById(id).orElseThrow(() -> new QuestionIsNotFoundException("Question with id {0} not found"));
    }
}
