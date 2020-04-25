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
    public QuestionDTO saveQuestion(@RequestBody Question question) {
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

    @PostMapping(value = "save/question/file")
    @ApiOperation("Сохранение вопроса с файлом")
    public void saveQuestionFile(@RequestPart("file") MultipartFile uploadedFile,
                                 @RequestParam(required = true, value = "id") Integer id) {
        Question question = questionService.findById(id);
        questionService.saveQuestionWithFile(uploadedFile, question);
    }

    @GetMapping(value = "get/file/{id}")
    @ApiModelProperty("Получить файлы по id вопроса")
    public List<QuestionFile> getFilesByQuestionId(@PathVariable Integer id) {
        return questionFileStorageService.findByQuestionId(id);
    }

    @GetMapping(value = "get/file/test/{id}")
    @ApiModelProperty("Получить файлы по id вопроса")
    public File getFilesByQuestionIdTest(@PathVariable Integer id) throws IOException {
        QuestionFile questionFile = questionFileStorageService.findByQuestionId(id).get(5);

        byte[] data = questionFile.getData();

        File file = new File("/Users/kirillmoiseenkov/Desktop/Всякий мусор/FastQuestion/sample.jpg");
        OutputStream os = new FileOutputStream(file);
        os.write(data);
        os.close();
        return file;
    }
}
