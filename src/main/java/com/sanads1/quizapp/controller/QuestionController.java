package com.sanads1.quizapp.controller;

import com.sanads1.quizapp.model.Question;
import com.sanads1.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
     private  final  QuestionService _service;
     public  QuestionController(QuestionService service){
          _service = service;
     }
     @GetMapping("allQuestions")
     public List<Question> getAllQuestions(){

         return _service.getAllQuestions();
     }
     @GetMapping("category/{category}")
     public List<Question> getQuestionsByCategory(@PathVariable String category){  //We didn't pass the variable to the path like @PathVariable("category") cause the mapping route name is the same thing as the dynamic path name i.e {category}.
          return _service.getQuestionsByCategory(category);
     }
}
