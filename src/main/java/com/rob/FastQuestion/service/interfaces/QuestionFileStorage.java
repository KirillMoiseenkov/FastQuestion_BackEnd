package com.rob.FastQuestion.service.interfaces;

import com.rob.FastQuestion.models.QuestionFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QuestionFileStorage {
    QuestionFile storageFile(MultipartFile file, String hashForName);

    QuestionFile saveQuestionFile(QuestionFile questionFile);

    QuestionFile getQuestionFilesRepo(Long id);

    List<QuestionFile> findAll();

    List<QuestionFile> findByQuestionId(Long id);

    void addVoteToQuestionFile(Long id);
}
