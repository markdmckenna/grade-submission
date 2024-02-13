package com.ltp.gradesubmission.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ltp.gradesubmission.Grade;

@Repository
public class GradeRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(GradeRepository.class);

    private List<Grade> studentGrades = new ArrayList<>();

    public Grade getGrade(int index) {
        LOGGER.info("[IN]GradeRepository - getGrade - index: {}", index);
        Grade grade = studentGrades.get(index);
        LOGGER.info("[OUT]GradeRepository - getGrade - grade: {}", grade);
        return grade;
    }

    public void addGrade(Grade grade) {
        studentGrades.add(grade);
    }

    public void updateGrade(int index, Grade grade) {
        studentGrades.set(index, grade);
    }

    public List<Grade> getGrades() {
        return studentGrades;
    }
    
}
