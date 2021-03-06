package com.rob.FastQuestion.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "answers")
@Data
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Id сущности")
    private Integer id;

    @Column(name = "text")
    @ApiModelProperty("Текст ответа")
    private String text;

    @Column(name = "lang")
    @ApiModelProperty("Язык")
    private String lang;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "question_id")
    @JsonBackReference
    @ApiModelProperty("Вопрос")
    private Question question;

}
