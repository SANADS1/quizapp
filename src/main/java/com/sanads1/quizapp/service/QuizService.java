package com.sanads1.quizapp.service;

import com.sanads1.quizapp.model.Question;
import com.sanads1.quizapp.model.QuestionWrapper;
import com.sanads1.quizapp.model.Quiz;
import com.sanads1.quizapp.model.Response;
import com.sanads1.quizapp.repository.QuestionRepository;
import com.sanads1.quizapp.repository.QuizRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizRepository _quizRepository;
    private final QuestionRepository _questionRepository;

    public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository){
        _quizRepository = quizRepository;
        _questionRepository = questionRepository;
    }

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = _questionRepository.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        _quizRepository.save(quiz);
        try {
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(String.format(null), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = _quizRepository.findById(id);
        List<Question> questionsFromDb = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for (Question q : questionsFromDb){
            QuestionWrapper qw = new QuestionWrapper(
                    q.getId(),
                    q.getQuestionTitle(),
                    q.getOption1(),
                    q.getOption2(),
                    q.getOption3(),
                    q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quiz = _quizRepository.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        int right = 0;
        int i = 0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;
            i++;
        }
        return  new ResponseEntity<>(right, HttpStatus.OK);
    }
}
