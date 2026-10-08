package com.priya.Quiz_App_SpringBoot.controller;

import com.priya.Quiz_App_SpringBoot.model.Question;
import com.priya.Quiz_App_SpringBoot.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
//        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
        //instead of doing here do in Service layer...
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{cat}") //if the name is not same here and below passing variable...
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable("cat") String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @PutMapping("/updateQuestion")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question){
        return questionService.updateQuestion(question);
    }

    @DeleteMapping("/deleteQuestionById/{id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable Integer id){
        return questionService.deleteQuestionById(id);
    }

}

//HTTP RESPONSE STATU CODES...(Mozilla Firefox Documentation)
// 1. Informational responses (100 - 199)
// 2. Successful responses (200 - 299)
// 3. Redirection messages (300 - 399)
// 4. Client error responses (400 - 499)
// 5. Server error responses (500 - 599)

