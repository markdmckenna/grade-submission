package com.ltp.gradesubmission;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ltp.gradesubmission.repository.GradeRepository;
import com.ltp.gradesubmission.service.GradeService;

@RunWith(MockitoJUnitRunner.class)
public class GradeServiceTest {
    
    @Mock
    GradeRepository gradeRepositoryMock;

    @InjectMocks
    private GradeService gradeService;

    @Test
    public void getGradesFromRepoTest() {
        when(gradeService.getGrades()).thenReturn(Arrays.asList(
            new Grade("Harry", "Potions", "C-"),
            new Grade("Dobbie", "Hell", "A-")
        ));
        List<Grade> grades = gradeService.getGrades();

        assertEquals("Harry", grades.get(0).getName());
    }

    @Test
    public void getGradeIndexTest() {
        Grade grade = new Grade("Harry", "Potions", "C-");

        when(gradeService.getGrades()).thenReturn(Arrays.asList(
            grade
        ));
        when(gradeRepositoryMock.getGrade(0)).thenReturn(grade);

        List<Grade> grades = gradeService.getGrades();

        int valid = this.gradeService.getGradeIndex(grades.get(0).getId());
        int notFound = this.gradeService.getGradeIndex("10");
    }

    @Test
    public void getGradeByIdTest() {
        Grade grade = new Grade("Harry", "Potions", "C-");

        when(gradeService.getGrades()).thenReturn(Arrays.asList(grade));
        when(gradeRepositoryMock.getGrade(0)).thenReturn(grade);
        String id = grade.getId();
        Grade result = this.gradeService.getGradeById(id);
        
        assertEquals(grade, result);
    }

    @Test 
    public void addGradeTest() {
        Grade grade = new Grade("Harry", "Potions", "C-");

        when(gradeService.getGrades()).thenReturn(Arrays.asList(grade));
        when(gradeRepositoryMock.getGrade(0)).thenReturn(grade);

        Grade newGrade = new Grade("Dobbie", "Disapperaing", "D");
        gradeService.submitGrade(newGrade);
        verify(gradeRepositoryMock, times(1)).addGrade(newGrade);
    }

    @Test
    public void updateGradeTest() {
        Grade grade = new Grade("Harry", "Potions", "C-");

        when(gradeService.getGrades()).thenReturn(Arrays.asList(grade));
        when(gradeRepositoryMock.getGrade(0)).thenReturn(grade);

        grade.setScore("A");
        gradeService.submitGrade(grade);
    }

}
