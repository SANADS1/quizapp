package com.sanads1.quizapp.controller;

import com.sanads1.quizapp.model.Question;
import com.sanads1.quizapp.model.QuestionWrapper;
import com.sanads1.quizapp.model.Response;
import com.sanads1.quizapp.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    private final QuizService _service;
    public QuizController(QuizService service){
        _service = service;
    }
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return _service.createQuiz(category, numQ, title);
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id){
       return   _service.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return _service.calculateResult(id, responses);
    }

}
