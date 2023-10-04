package com.sanads1.quizapp.service;

import com.sanads1.quizapp.repository.QuestionRepository;
import com.sanads1.quizapp.model.Question;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class QuestionService {
    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);
    private final QuestionRepository _question;

    public QuestionService(QuestionRepository questionRepository){
        _question = questionRepository;
    }
    public List<Question> getAllQuestions() {
       return  _question.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        logger.info("Fetching questions for category: {}", category);
        List<Question> questions = _question.findByCategoryIgnoreCase(category);
        logger.info("Found {} questions for category: {}", questions.size(), category);
        return questions;
    }
}
