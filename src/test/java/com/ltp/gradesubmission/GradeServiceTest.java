package com.ltp.gradesubmission;

import static org.junit.Assert.assertEquals;
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
            new Grade("1", "Harry", "Potions", "C-"),
            new Grade("2", "Dobbie", "Hell", "A-")
        ));
        List<Grade> grades = gradeService.getGrades();

        assertEquals("Harry", grades.get(0).getName());
    }

}
