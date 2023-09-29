package com.sanads1.quizapp.service;

import com.sanads1.quizapp.repository.QuestionRepository;
import com.sanads1.quizapp.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository _question;

    public QuestionService(QuestionRepository questionRepository){
        _question = questionRepository;
    }
    public List<Question> getAllService() {
       return  _question.findAll();
    }
}
