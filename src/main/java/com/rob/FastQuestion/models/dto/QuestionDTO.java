package com.rob.FastQuestion.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rob.FastQuestion.models.Answer;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDTO {

    @ApiModelProperty("Id сущности")
    private Integer id;

    @ApiModelProperty("Текст вопроса")
    private String text;

    @ApiModelProperty("Список ответов на вопрос")
    private List<Answer> answers = new ArrayList<>();
}
