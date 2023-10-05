package com.sanads1.quizapp.service;

import ExceptionHandling.ResourceNotFoundException;
import com.sanads1.quizapp.repository.QuestionRepository;
import com.sanads1.quizapp.model.Question;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.Optional;

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

    public String addQuestion(Question question){
        _question.save(question);
        return "success";
    }

    public String updateQuestion(Question updatedQuestion) {
        // Check if the question with the given ID exists in the database
        Optional<Question> existingQuestion = _question.findById(updatedQuestion.getId());

        if (existingQuestion.isPresent()) {
            // Perform the update
            _question.save(updatedQuestion);
            return "success";
        } else {
            // Handle the case where the question with the given ID doesn't exist
            throw new ResourceNotFoundException("Question not found");
        }
    }

    public Optional<Question> getQuestionById(Integer id){
        Optional<Question> question = _question.findById(id);

        if(question.isPresent()){
            return question;
        }else {
            // Handle the case where the question with the given ID doesn't exist
            throw new ResourceNotFoundException("Question with the Id cannot be found");
        }

    }

    public void deleteQuestion(Integer id) {
        Optional<Question> question = _question.findById(id);
        if (question.isPresent()) {
            _question.deleteById(id);
        } else {
            // Handle the case where the question with the given ID doesn't exist
            throw new ResourceNotFoundException("Question with the ID " + id + " not found");
        }
    }



}
