package com.rob.FastQuestion.controller;

import com.rob.FastQuestion.models.Question;
import com.rob.FastQuestion.models.QuestionFile;
import com.rob.FastQuestion.models.dto.QuestionDTO;
import com.rob.FastQuestion.repo.QuestionFilesRepo;
import com.rob.FastQuestion.service.QuestionFileStorageService;
import com.rob.FastQuestion.service.QuestionService;
import com.rob.FastQuestion.service.interfaces.IQuestionService;
import com.rob.FastQuestion.service.utils.QuestionUtils;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/question")
public class QestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionFileStorageService questionFileStorageService;

    @PostMapping(value = "/saveQuestion")
    @ApiOperation("Сохранить вопрос")
    public QuestionDTO saveQuestion(@RequestBody Question question) { //todo сделать через QuestionDTO
        return QuestionUtils.mapQuestionToQuestionDTO(questionService.saveQuestion(question));
    }

    @GetMapping(value = "/getRandomQuestion")
    @ApiOperation("Получить случайный вопрос")
    public QuestionDTO getRandomQuestion() {
        return questionService.getQuestionDTOByQuestionWithFiles(questionService.getRandomQuestion());
    }

    @GetMapping(value = "/getAllQuestions")
    @ApiOperation("Получить все вопросы")
    public List<QuestionDTO> getAllQuestion() {
        return questionService.findAll().stream().map(question ->
                questionService.getQuestionDTOByQuestionWithFiles(question))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "save/question/file/{id}")
    @ApiOperation("Сохранение вопроса с файлом")
    @ResponseStatus(HttpStatus.OK)
    public void saveQuestionFile(@RequestParam("file") MultipartFile uploadedFile,
                                 @PathVariable(required = true, value = "id") Long id) {
        Question question = questionService.findById(id);
        questionService.saveQuestionWithFile(uploadedFile, question);
    }

    @GetMapping(value = "get/file/{id}")
    @ApiModelProperty("Получить файлы по id вопроса")
    public List<QuestionFile> getFilesByQuestionId(@PathVariable Long id) {
        return questionFileStorageService.findByQuestionId(id);
    }

    @GetMapping(value = "get/question/{id}")
    @ApiModelProperty("Получить вопрос по id")
    public QuestionDTO getQuestionById(@PathVariable(value = "id") Long id) {
        return questionService.getQuestionDTOByQuestionWithFiles(questionService.findById(id));
    }

    @GetMapping(value = "get/file/by-file/{id}")
    @ApiModelProperty("Получить файл по id файла")
    public QuestionFile getFilesById(@PathVariable Long id) {
        return questionFileStorageService.getQuestionFileById(id);
    }

}
