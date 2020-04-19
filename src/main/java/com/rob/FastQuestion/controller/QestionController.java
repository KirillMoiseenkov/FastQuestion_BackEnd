package com.rob.FastQuestion.controller;

import com.rob.FastQuestion.models.Question;
import com.rob.FastQuestion.service.interfaces.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


@RestController
public class QestionController {

    @Autowired
    IQuestionService questionService;

    @PostMapping(value = "/saveQuestion")
    public Question saveQuestion(@RequestBody Question question) {
        return questionService.saveQuestion(question);
    }

    @GetMapping(value = "/getRandomQuestion")
    public Question getRandomQuestion() {
        return questionService.getRandomQuestion();
    }

    @GetMapping(value = "/getAllQuestion")
    public Question getAllQuestion() {
        return questionService.findAll().get(0);
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
