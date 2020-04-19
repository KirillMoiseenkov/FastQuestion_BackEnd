package com.rob.FastQuestion.models;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "text")
    private String text;

    @OneToMany(mappedBy = "question")
    @ToString.Exclude
    private List<Answer> answers = new ArrayList<>();

    public void addToAnswers(Answer answer) {
        if (answer != null) {
            answers.add(answer);
            answer.setQuestion(this);
        }
    }
}
