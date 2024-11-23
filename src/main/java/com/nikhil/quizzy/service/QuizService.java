package com.nikhil.quizzy.service;
import com.nikhil.quizzy.model.Question;
import com.nikhil.quizzy.model.QuestionWrapper;
import com.nikhil.quizzy.model.Quiz;
import com.nikhil.quizzy.model.Response;
import com.nikhil.quizzy.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizRepo quizRepo;
    public ResponseEntity<String> createQuiz(String category,int numQ,String title) {
        List<Question> questions = quizRepo.findRandomQuestionsByCategory(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepo.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz=quizRepo.findById(id);
        List<Question> questions=quiz.get().getQuestions();
        List<QuestionWrapper> questionWrappers=new ArrayList<>();
        for(Question q:questions){
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionWrappers.add(qw);
        }
        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id,List<Response> responses){
        Quiz quiz=quizRepo.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        int count=0;
        int i=0;
        for(Response r:responses){
            if(r.getResponse().equals(questions.get(i).getRightAnswer())){
                count++;
            }
            i++;

        }
        return new ResponseEntity<>(count,HttpStatus.OK);

    }
}
