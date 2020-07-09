package com.rob.FastQuestion.service;

import com.rob.FastQuestion.exception.FileNotFoundException;
import com.rob.FastQuestion.exception.FileStorageException;
import com.rob.FastQuestion.models.QuestionFile;
import com.rob.FastQuestion.repo.QuestionFilesRepo;
import com.rob.FastQuestion.service.interfaces.QuestionFileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class QuestionFileStorageService implements QuestionFileStorage {
    @Autowired
    QuestionFilesRepo questionFilesRepo;

    @Autowired
    FileSaverService fileSaverService;

    @Transactional(rollbackOn = IOException.class)
    public QuestionFile storageFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        QuestionFile qFile = new QuestionFile();
        qFile.setFileName(fileName);
        fileSaverService.saveFile(fileName, file);
        qFile.setFileType(file.getContentType());

        return questionFilesRepo.save(qFile);
    }

    public QuestionFile saveQuestionFile(QuestionFile questionFile) {
        return questionFilesRepo.save(questionFile);
    }

    public QuestionFile getQuestionFilesRepo(Long id) {
        return questionFilesRepo.findById(id).orElseThrow(() -> new FileNotFoundException("File with id {0} not found"));
    }

    public List<QuestionFile> findAll() {
        return questionFilesRepo.findAll();
    }

    @Transactional
    public List<QuestionFile> findByQuestionId(Long id) {
        return questionFilesRepo.findByQuestionId(id);
    }

    public void addVoteToQuestionFile(Long id) {
        QuestionFile questionFile = getQuestionFilesRepo(id);
        questionFile.setCount(questionFile.getCount() + 1);
        saveQuestionFile(questionFile);
    }


}
