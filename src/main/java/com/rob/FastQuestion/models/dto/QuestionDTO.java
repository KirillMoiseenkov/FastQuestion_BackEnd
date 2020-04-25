package com.rob.FastQuestion.models.dto;

import com.rob.FastQuestion.models.Answer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionDTO {

    @ApiModelProperty("Id сущности")
    private Integer id;

    @ApiModelProperty("Текст вопроса")
    private String text;

    @ApiModelProperty("Язык")
    private String lang;

    @ApiModelProperty("Список ответов на вопрос")
    private List<Answer> answers = new ArrayList<>();
}
