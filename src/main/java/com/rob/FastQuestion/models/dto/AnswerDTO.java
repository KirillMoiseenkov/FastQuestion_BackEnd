package com.rob.FastQuestion.models.dto;

import com.rob.FastQuestion.models.Question;
import io.swagger.annotations.ApiModelProperty;

public class AnswerDTO {
    @ApiModelProperty("Id сущности")
    private Integer id;

    @ApiModelProperty("Текст ответа")
    private String text;

    @ApiModelProperty("Вопрос")
    private Question question;
}
