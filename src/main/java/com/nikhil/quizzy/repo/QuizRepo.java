package com.nikhil.quizzy.repo;

import com.nikhil.quizzy.model.Question;
import com.nikhil.quizzy.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuizRepo extends JpaRepository<Quiz,Integer> {
    @Query(value="SELECT * FROM question q WHERE q.category=:category order by random() limit :numQ",nativeQuery = true)
     List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
