package com.rob.FastQuestion.models.dto;

import com.rob.FastQuestion.models.Answer;
import com.rob.FastQuestion.models.QuestionType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class QuestionDTO {

    @ApiModelProperty("Id сущности")
    private Long id;

    @ApiModelProperty("Текст вопроса")
    private String text;

    @ApiModelProperty("Язык")
    private String lang;

    @ApiModelProperty("Список ответов на вопрос")
    private List<Answer> answers = new ArrayList<>();

    @ApiModelProperty("id файлов и их голоса")
    private Map<Long, Long> fileIds = new HashMap<>();

    @ApiModelProperty("Тип вополса")
    private QuestionType questionType;
}
