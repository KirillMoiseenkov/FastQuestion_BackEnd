package com.rob.FastQuestion.controller;

import com.rob.FastQuestion.models.Question;
import com.rob.FastQuestion.models.QuestionFile;
import com.rob.FastQuestion.models.dto.QuestionDTO;
import com.rob.FastQuestion.repo.QuestionFilesRepo;
import com.rob.FastQuestion.service.QuestionFileStorageService;
import com.rob.FastQuestion.service.interfaces.IQuestionService;
import com.rob.FastQuestion.service.utils.QuestionUtils;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequestMapping("/api/v1/question")
public class QestionController {

    @Autowired
    IQuestionService questionService;

    @Autowired
    QuestionFileStorageService questionFileStorageService;

    @PostMapping(value = "/saveQuestion")
    @ApiOperation("Сохранить вопрос")
    public QuestionDTO saveQuestion(@RequestBody Question question) { //сделать QuestionDTO
        return QuestionUtils.mapQuestionToQuestionDTO(questionService.saveQuestion(question));
    }

    @GetMapping(value = "/getRandomQuestion")
    @ApiOperation("Получить случайный вопрос")
    public QuestionDTO getRandomQuestion() {
        return QuestionUtils.mapQuestionToQuestionDTO(questionService.getRandomQuestion());
    }

    @GetMapping(value = "/getAllQuestions")
    @ApiOperation("Получить все вопросы")
    public List<QuestionDTO> getAllQuestion() {
        return QuestionUtils.mapQuestionToQuestionDTO(questionService.findAll());
    }

    @PostMapping(value = "save/question/file/{id}")
    @ApiOperation("Сохранение вопроса с файлом")
    public void saveQuestionFile(@RequestParam("file") MultipartFile uploadedFile,
                                 @PathVariable(required = true, value = "id") Integer id) {
        Question question = questionService.findById(id);
        questionService.saveQuestionWithFile(uploadedFile, question);
    }

    @GetMapping(value = "get/file/{id}")
    @ApiModelProperty("Получить файлы по id вопроса")
    public List<QuestionFile> getFilesByQuestionId(@PathVariable Integer id) {
        return questionFileStorageService.findByQuestionId(id);
    }
}
