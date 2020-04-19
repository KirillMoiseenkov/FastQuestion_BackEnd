package com.rob.FastQuestion.repo;

import com.rob.FastQuestion.models.Answer;
import com.rob.FastQuestion.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepo extends JpaRepository<Answer, Integer> {
    List<Answer> findAnswersByQuestion(Question question);

    List<Answer> findAnswersByQuestionText(String text);

    List<Answer> findAnswersByQuestionId(Integer id);
}
