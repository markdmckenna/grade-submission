package com.ltp.gradesubmission.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ltp.gradesubmission.Constants;
import com.ltp.gradesubmission.Grade;
import com.ltp.gradesubmission.repository.GradeRepository;

@Service
public class GradeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GradeService.class);

    GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public Grade getGrade(int index) {
        LOGGER.info("[IN]GradeService - getGrade - index: {}", index);
        Grade grade = this.gradeRepository.getGrade(index);
        LOGGER.info("[OUT]GradeService - getGrade - grade: {}", grade);
        return grade;
    }

    public void addGrade(Grade grade) {
        this.gradeRepository.addGrade(grade);
    }

    public void updateGrade(Grade grade, int index) {
        this.gradeRepository.updateGrade(index, grade);
    }

    public List<Grade> getGrades() {
        return this.gradeRepository.getGrades();
    }

    private int getGradeIndex(String id) {
        LOGGER.warn("[IN]GradeService - getGradeIndex - id: {}", id);
        for (int i = 0; i < this.getGrades().size(); i++) {
            if (this.getGrade(i).getId().equals(id))
                return i;
        }
        return Constants.NOT_FOUND;
    }

    public Grade getGradeById(String id) {
        LOGGER.info("[IN]GradeService - getGradeById - id: {}", id);
        int index = this.getGradeIndex(id);
        Grade grade = index == Constants.NOT_FOUND ? new Grade() : this.getGrade(index);
        LOGGER.info("[OUT]GradeService - getGradeById - grade: {}", grade);
        return grade;
    }

    public void submitGrade(Grade grade) {
        LOGGER.info("[IN]GradeService - submitGrade - grade: {}", grade);
        int index = this.getGradeIndex(grade.getId());

        if (index == Constants.NOT_FOUND) {
            this.addGrade(grade);
        } else {
            this.updateGrade(grade, index);
        }
    }
    

}
