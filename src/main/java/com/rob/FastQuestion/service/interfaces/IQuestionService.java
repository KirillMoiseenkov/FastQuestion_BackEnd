package com.rob.FastQuestion.service.interfaces;

import com.rob.FastQuestion.models.Question;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IQuestionService {
    Question saveQuestion(Question question);

    List<Question> findAll();

    Question getRandomQuestion();

    Question saveQuestionWithFile(MultipartFile multipartFile, Question question);

    Question findById(Integer id);
}
