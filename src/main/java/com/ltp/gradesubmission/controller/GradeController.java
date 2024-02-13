package com.ltp.gradesubmission.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ltp.gradesubmission.Grade;
import com.ltp.gradesubmission.service.GradeService;

import jakarta.validation.Valid;

@Controller
public class GradeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GradeController.class);

    GradeService gradeService = new GradeService();

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        model.addAttribute("grade", this.gradeService.getGradeById(id));
        return "form";
    }

    @PostMapping("/handleSubmit")
    public String submitForm(@Valid Grade grade, BindingResult bindingResult) {
        LOGGER.info("[IN]GradeController - submitForm - grade: {} - bindResult.hasErrors(): {}", grade, bindingResult.hasErrors());
        if (bindingResult.hasErrors()) return "Form";
        this.gradeService.submitGrade(grade);
        return "redirect:/grades";
    }

    @GetMapping("/grades")
    public String getGrades(Model model) {
        LOGGER.info("[IN]GradeController - getGrades - model: {}", model);
        model.addAttribute("grades", this.gradeService.getGrades());
        List<Grade> grades = this.gradeService.getGrades();
        LOGGER.info("[OUT]GradeController - getGrades - grades: {}", grades);
        return "grades";
    }

}
