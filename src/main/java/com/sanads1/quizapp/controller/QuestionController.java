package com.sanads1.quizapp.controller;

import com.sanads1.quizapp.model.Question;
import com.sanads1.quizapp.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("question")
public class QuestionController {
     private  final  QuestionService _service;
     public  QuestionController(QuestionService service){
          _service = service;
     }
     @GetMapping("allQuestions")
     public ResponseEntity<List<Question>> getAllQuestions(){

         return _service.getAllQuestions();
     }
     @GetMapping("{id}")
     public Optional<Question> getQuestionById(@PathVariable("id") Integer id){
        return _service.getQuestionById(id);
     }

     @GetMapping("category/{category}")
     public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){  //I didn't pass the variable to the path like @PathVariable("category") cause the mapping route name is the same thing as the dynamic path name i.e {category}.
          return _service.getQuestionsByCategory(category);
     }

     @PostMapping("add")
     public ResponseEntity<String> addQuestion(@RequestBody Question question){
          return _service.addQuestion(question);
     }

     @PutMapping("update/{id}")
     public String updateQuestion(@PathVariable("id") Integer id, @RequestBody Question updatedQuestion) {
          // Here, you should set the ID of the updatedQuestion to the provided id
          updatedQuestion.setId(id);

          return _service.updateQuestion(updatedQuestion);
     }
     @DeleteMapping("{id}")
     public void deleteQuestion(@PathVariable("id") Integer id){
        _service.deleteQuestion(id);
     }

}
