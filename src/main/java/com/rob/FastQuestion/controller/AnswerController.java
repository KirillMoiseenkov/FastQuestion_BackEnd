package com.rob.FastQuestion.controller;

import com.rob.FastQuestion.config.message.FQMessageSource;
import com.rob.FastQuestion.exception.AnswerQuestionNullException;
import com.rob.FastQuestion.models.Answer;
import com.rob.FastQuestion.models.Question;
import com.rob.FastQuestion.models.QuestionFile;
import com.rob.FastQuestion.service.AnswerService;
import com.rob.FastQuestion.service.QuestionFileStorageService;
import com.rob.FastQuestion.service.QuestionService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/answer")
@Slf4j
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionFileStorageService fileStorageService;

    @Autowired
    FQMessageSource messageSource;

    @GetMapping(value = "/getAnswers")
    @ApiOperation("Получить список всех ответов")
    public List<Answer> getAnswers() {
        log.debug(messageSource.get("answer.get.all"));
        return answerService.findAll();
    }


    @PostMapping(value = "save/file/vote/{id}")
    @ApiOperation("Сохранить голос")
    @ResponseStatus(HttpStatus.OK)
    public void saveVote(@PathVariable Long id) {
        QuestionFile questionFile = fileStorageService.getQuestionFilesRepo(id);
        if (questionFile.getCount() == null) {
            questionFile.setCount(0l);
        }
        questionFile.setCount(questionFile.getCount() + 1);
        fileStorageService.saveQuestionFile(questionFile);
    }


    @PostMapping(value = "/saveAnswer", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Сохранить ответ на вопрос")
    public Answer saveAnswer(@RequestBody Answer answer) {
        Question question = questionService.findById(answer.getQuestion().getId());
        if (question != null) {
            if (answerService.getCountOfAnswers() != 0) {
                question.setProbability(((double) 1 - ((double) question.getAnswers().size() / ((double) answerService.getCountOfAnswers()))));
                questionService.saveQuestion(question);
            }
            return answerService.saveAnswer(answer);
        }
        throw new AnswerQuestionNullException("Question is not exist");
    }

    @PostMapping(value = "/get/answer/by/question")
    @ApiOperation("Получить ответ по вопросу")
    public List<Answer> getAnswerByQuestion(@RequestBody Question question) {
        return answerService.getAnswersByQuestion(question);
    }

    @PostMapping(value = "/get/answer/by/question/text")
    @ApiOperation("Получить ответ по тексту вопроса")
    public List<Answer> getAnswerByQuestionText(@RequestBody Question question) {
        return answerService.getAnswersByQuestionText(question.getText());
    }

    @GetMapping(value = "/get/answer/by/question/{id}")
    @ApiOperation("Получить ответ на вопрос по Id вопроса")
    public List<Answer> getAnswerByQuestionId(@PathVariable("id") Long id) {
        return answerService.getAnswersByQuestionId(id);
    }
}
