package com.rob.FastQuestion.repo;

import com.rob.FastQuestion.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepo extends JpaRepository<Question, Integer> {
    @Query(value="SELECT * FROM QUESTIONS ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Question getRandomQuestion();

}
