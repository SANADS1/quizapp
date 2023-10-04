/*
package com.sanads1.quizapp.data;

import com.sanads1.quizapp.repository.QuestionRepository;
import com.sanads1.quizapp.model.Question;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DummyDataSeeder {

    private final QuestionRepository _questionRepository;

    @Autowired
    public DummyDataSeeder(QuestionRepository questionRepository) {
        _questionRepository = questionRepository;
    }

    @PostConstruct
    public void seedDummyData() {
        // Generate and save dummy questions
        List<Question> dummyQuestions = generateDummyQuestions();
        _questionRepository.saveAll(dummyQuestions);
    }

    private List<Question> generateDummyQuestions() {
        List<Question> questions = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            Question question = new Question();
            question.setQuestionTitle("Question " + i);
            question.setOption1("Option 1 for Question " + i);
            question.setOption2("Option 2 for Question " + i);
            question.setOption3("Option 3 for Question " + i);
            question.setOption4("Option 4 for Question " + i);
            question.setRightAnswer("Option " + (i % 4 + 1));
            question.setDifficultyLevel("Medium"); // Set the desired difficulty level
            question.setCategory("General"); // Set the desired category

            questions.add(question);
        }

        return questions;
    }
}
*/
