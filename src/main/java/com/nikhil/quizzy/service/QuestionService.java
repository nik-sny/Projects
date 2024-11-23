package com.nikhil.quizzy.service;

import com.nikhil.quizzy.model.Question;
import com.nikhil.quizzy.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepo questionRepo;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
        return new ResponseEntity<>(questionRepo.findAll(),HttpStatus.OK);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Question>> getByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepo.findByCategory(category), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> addQuestion(Question question) {
         questionRepo.save(question);
         return new ResponseEntity<>("success",HttpStatus.CREATED);

    }
    public ResponseEntity<List<Question>> getByDifficulty(String difficulty) {
        try {
            return new ResponseEntity<>(questionRepo.findByDifficultylevel(difficulty), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

}
