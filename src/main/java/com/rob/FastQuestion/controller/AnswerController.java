package com.rob.FastQuestion.controller;

import com.google.common.primitives.Longs;
import com.rob.FastQuestion.exception.AnswerQuestionNullException;
import com.rob.FastQuestion.models.Answer;
import com.rob.FastQuestion.models.Question;
import com.rob.FastQuestion.models.QuestionFile;
import com.rob.FastQuestion.service.AnswerService;
import com.rob.FastQuestion.service.QuestionFileStorageService;
import com.rob.FastQuestion.service.QuestionService;
import com.rob.FastQuestion.service.interfaces.IAnswerService;
import com.rob.FastQuestion.service.interfaces.IQuestionService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionFileStorageService fileStorageService;

    @GetMapping(value = "/getAnswers")
    @ApiOperation("Получить список вскех вопросов")
    public List<Answer> getAnswers() {
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

    @PostMapping(value = "/getAnswerByQuestion")
    @ApiOperation("Получить ответ по вопросу")
    public List<Answer> getAnswerByQuestion(@RequestBody Question question) {
        return answerService.getAnswersByQuestion(question);
    }

    @PostMapping(value = "/getAnswerByQuestionText")
    @ApiOperation("Получить ответ по тексту вопроса")
    public List<Answer> getAnswerByQuestionText(@RequestBody Question question) {
        return answerService.getAnswersByQuestionText(question.getText());
    }

    @GetMapping(value = "/getAnswerByQuestionId/{id}")
    @ApiOperation("Получить ответ на вопрос по Id вопроса")
    public List<Answer> getAnswerByQuestionId(@PathVariable("id") Long id) {
        return answerService.getAnswersByQuestionId(id);
    }
}
