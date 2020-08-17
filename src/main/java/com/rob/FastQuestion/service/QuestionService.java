package com.rob.FastQuestion.service;

import com.rob.FastQuestion.exception.FileNotFoundException;
import com.rob.FastQuestion.exception.QuestionIsNotFoundException;
import com.rob.FastQuestion.models.Question;
import com.rob.FastQuestion.models.QuestionFile;
import com.rob.FastQuestion.models.dto.QuestionDTO;
import com.rob.FastQuestion.repo.QuestionRepo;
import com.rob.FastQuestion.service.utils.QuestionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepo questionRepo;
    private final QuestionFileStorageService questionFileStorageService;
    private static final Random doubleRandomGenerator = new Random();

    public Question saveQuestion(Question question) {
        return questionRepo.save(question);
    }

    public List<Question> findAll() {
        return questionRepo.findAll();
    }

    public Question getRandomQuestion() {
//        double randomValue = 1 * doubleRandomGenerator.nextDouble();
//        Double probability = RandomResistance.addToProbabilityResistance(randomValue);
//
//
//        Question question = questionRepo.findFirstByProbabilityGreaterThanOrderByProbabilityDesc(probability);
//        if (question == null)
//            question = questionRepo.findFirstByProbabilityLessThanOrderByProbabilityDesc(probability);

//        return question;
        return questionRepo.getRandomQuestion();
    }

    public Question saveQuestionWithFile(MultipartFile multipartFile, Question question) {
        QuestionFile questionFile = questionFileStorageService.storageFile(multipartFile);
        question.setQuestionFile(questionFile);
        return questionRepo.save(question);
    }

    public Question findById(Long id) {
        return questionRepo.findById(id).orElseThrow(() -> new QuestionIsNotFoundException("Question with id {0} not found"));
    }

    public QuestionDTO MapQuestionToQuestionDTO(Question question) {
        return QuestionUtils.mapQuestionToQuestionDTO(question);
    }

    public QuestionDTO getQuestionDTOByQuestionWithFiles(Question question) {
        List<QuestionFile> questionFileList = questionFileStorageService.findByQuestionId(question.getId());
        if (questionFileList == null) {
            throw new FileNotFoundException("File not found");
        }

        return QuestionUtils.mapQuestionToQuestionDTO(question, questionFileList);
    }

}
