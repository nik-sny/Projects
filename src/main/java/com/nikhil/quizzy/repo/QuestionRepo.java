package com.nikhil.quizzy.repo;

import com.nikhil.quizzy.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {
    public List<Question> findByCategory(String category);
    public List<Question> findByDifficultylevel(String difficultylevel);

}
