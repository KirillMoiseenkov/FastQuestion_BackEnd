package com.rob.FastQuestion.controller;

import com.rob.FastQuestion.models.Question;
import com.rob.FastQuestion.service.interfaces.IQuestionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@RestController
public class QestionController {

    @Autowired
    IQuestionService questionService;

    @PostMapping(value = "/saveQuestion")
    @ApiOperation("Сохранить вопрос")
    public Question saveQuestion(@RequestBody Question question) {
        return questionService.saveQuestion(question);
    }

    @GetMapping(value = "/getRandomQuestion")
    @ApiOperation("Получить случайный вопрос")
    public Question getRandomQuestion() {
        return questionService.getRandomQuestion();
    }

    @GetMapping(value = "/getAllQuestions")
    @ApiOperation("Получить все вопросы")
    public List<Question> getAllQuestion() {
        return questionService.findAll();
    }

    @PostMapping(value = "saveFile")
    public void saveFile(@RequestParam("file") MultipartFile uploadedFile) {
        File file;
        try {
            InputStream inputStream = uploadedFile.getInputStream();

            String outputStream = "/Users/kirillmoiseenkov/Desktop/FastQuestion/src/main/resources";

            Files.copy(uploadedFile.getInputStream(), Paths.get(outputStream));
            file = new File(outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
