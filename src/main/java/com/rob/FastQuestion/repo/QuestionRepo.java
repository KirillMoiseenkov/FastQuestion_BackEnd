package com.rob.FastQuestion.repo;

import com.rob.FastQuestion.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepo extends JpaRepository<Question, Long> {
    @Query(value = "SELECT * FROM QUESTIONS ORDER BY random() LIMIT 1", nativeQuery = true)
    Question getRandomQuestion();

    @Query(value = "SELECT COUNT(*) FROM QUESTIONS", nativeQuery = true)
    Integer getCountOfQuestions();

    @Query(value = "Select * from questions where probability > ?1 order by random() limit 1 ", nativeQuery = true)
    Question findFirstByProbabilityGreaterThanOrderByProbabilityDesc(Double probability);

    @Query(value = "Select * from questions where probability < ?1 order by random() limit 1 ", nativeQuery = true)
    Question findFirstByProbabilityLessThanOrderByProbabilityDesc(Double probability);
}
