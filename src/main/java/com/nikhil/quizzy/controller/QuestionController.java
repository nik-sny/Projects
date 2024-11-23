package com.nikhil.quizzy.controller;

import com.nikhil.quizzy.model.Question;
import com.nikhil.quizzy.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    private  QuestionService questionService;


    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> allQuestions() {
        return  questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> category(@PathVariable String category) {
        return questionService.getByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }
    @GetMapping("difficulty/{difficulty}")
    public ResponseEntity<List<Question>> difficulty(@PathVariable String difficulty) {
        return questionService.getByDifficulty(difficulty);
    }
}
