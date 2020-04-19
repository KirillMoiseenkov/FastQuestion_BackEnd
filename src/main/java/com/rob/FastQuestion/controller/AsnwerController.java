package com.rob.FastQuestion.controller;

import com.rob.FastQuestion.models.Answer;
import com.rob.FastQuestion.models.Question;
import com.rob.FastQuestion.service.interfaces.IAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AsnwerController {

    @Autowired
    private IAnswerService answerService;

    @GetMapping(value = "/getAnswers")
    public List<Answer> getAnswers() {
        return answerService.findAll();
    }

    @PostMapping(value = "/saveAnswer")
    public Answer saveAnswer(@RequestBody Answer answer) {
        return answerService.saveAnswer(answer);
    }

    @PostMapping(value = "/getAnswerByQuestion")
    public List<Answer> getAnswerByQuestion(@RequestBody Question question) {
        return answerService.getAnswersByQuestion(question);
    }

    @PostMapping(value = "/getAnswerByQuestionText")
    public List<Answer> getAnswerByQuestionText(@RequestBody Question question) {
        return answerService.getAnswersByQuestionText(question.getText());
    }

    @PostMapping(value = "/getAnswerByQuestionId/{id}")
    public List<Answer> getAnswerByQuestionId(@RequestParam("id") Integer id) {
        return answerService.getAnswersByQuestionId(id);
    }

    @GetMapping(value = "/getNewAnswerByQuestion")
    public List<Answer> getNewAnswerByQuestion(Integer count) {
        return null;
    }
}
