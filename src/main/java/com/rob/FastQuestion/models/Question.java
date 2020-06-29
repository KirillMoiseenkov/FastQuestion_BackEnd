package com.rob.FastQuestion.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Id сущности")
    private Long id;

    @Column(name = "text")
    @ApiModelProperty("Текст вопроса")
    private String text;

    @Column(name = "lang")
    @ApiModelProperty("Язык")
    private String lang;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference
    @ApiModelProperty("Список ответов на вопрос")
    private List<Answer> answers = new ArrayList<>();

    @Column(name = "probability")
    @ApiModelProperty("Вероятность")
    private Double probability = 0d;

    public void addToAnswers(Answer answer) {
        if (answer != null) {
            answers.add(answer);
            answer.setQuestion(this);
        }
    }

    @Column(name = "type_of_question")
    @ApiModelProperty("Тип вопроса")
    private QuestionType questionType;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    @ToString.Exclude
    //@JsonManagedReference
    @ApiModelProperty("Файлы")
    private List<QuestionFile> questionFiles = new ArrayList<>();

    public void setQuestionFile(QuestionFile questionFile) {
        questionFiles.add(questionFile);
        questionFile.setQuestion(this);
    }
}
