package com.rob.FastQuestion.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @ApiModelProperty("Id сущности")
    private Integer id;

    @Column(name = "text")
    @ApiModelProperty("Текст вопроса")
    private String text;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonBackReference
    @ApiModelProperty("Список ответов на вопрос")
    private List<Answer> answers = new ArrayList<>();

    public void addToAnswers(Answer answer) {
        if (answer != null) {
            answers.add(answer);
            answer.setQuestion(this);
        }
    }
}
