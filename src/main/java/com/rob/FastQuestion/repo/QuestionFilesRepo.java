package com.rob.FastQuestion.repo;

import com.rob.FastQuestion.models.QuestionFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionFilesRepo extends JpaRepository<QuestionFile, Long> {
    List<QuestionFile> findByQuestionId(Long id);
}
