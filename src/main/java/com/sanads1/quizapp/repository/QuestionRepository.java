package com.sanads1.quizapp.repository;

import com.sanads1.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
     // this defined overload method findByCategory will work perfectly as one of the JpaRepository overloaded methods cause our database table contain category as one of its Column and you can also use Jpa query (JPQL) to write a query to do that but JpaRepository will use the Category in the named method to query our Db for the data with the category type.
     @Query("SELECT q FROM Question q WHERE lower(q.category) = lower(:category)")
     List<Question> findByCategoryIgnoreCase(@Param("category") String category);

     @Query(value = "SELECT * FROM Question q WHERE lower(q.category)= lower(:category) ORDER BY RANDOM() LIMIT:numQ", nativeQuery = true)
     List<Question> findRandomQuestionsByCategory(String category, int numQ);

}

