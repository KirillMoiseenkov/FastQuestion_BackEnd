package com.rob.FastQuestion.controller;

import com.rob.FastQuestion.models.Answer;
import com.rob.FastQuestion.models.Question;
import com.rob.FastQuestion.service.interfaces.IAnswerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AsnwerController {

    @Autowired
    private IAnswerService answerService;

    @GetMapping(value = "/getAnswers")
    @ApiOperation("Получить список вскех вопросов")
    public List<Answer> getAnswers() {
        return answerService.findAll();
    }

    @PostMapping(value = "/saveAnswer")
    @ApiOperation("Сохранить ответ на вопрос")
    public Answer saveAnswer(@RequestBody Answer answer) {
        return answerService.saveAnswer(answer);
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
    public List<Answer> getAnswerByQuestionId(@RequestParam("id") Integer id) {
        return answerService.getAnswersByQuestionId(id);
    }

//    @GetMapping(value = "/getNewAnswerByQuestion")
//    @ApiOperation("")
//    public List<Answer> getNewAnswerByQuestion(Integer count) {
//        return null;
//    }
}
