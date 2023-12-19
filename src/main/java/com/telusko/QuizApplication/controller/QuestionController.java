package com.telusko.QuizApplication.controller;

import com.telusko.QuizApplication.model.Question;
import com.telusko.QuizApplication.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
       return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public String addQuestion(@RequestBody  Question question){
        return questionService.addQuestion(question);
    }

    @PutMapping("allQuestions/{QuestionId}")
    public String updateQuestion(@PathVariable("QuestionId") int id, @RequestBody Question question){
        return questionService.updateQuestion(id, question);
    }

    @DeleteMapping("allQuestions/{QuestionId}")
    public void deleteQuestion(@PathVariable("QuestionId") int id){
         questionService.deleteQuestion(id);
    }
}
