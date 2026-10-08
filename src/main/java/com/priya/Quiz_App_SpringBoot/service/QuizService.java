package com.priya.Quiz_App_SpringBoot.service;

import com.priya.Quiz_App_SpringBoot.model.Question;
import com.priya.Quiz_App_SpringBoot.model.QuestionWrapper;
import com.priya.Quiz_App_SpringBoot.model.Quiz;
import com.priya.Quiz_App_SpringBoot.model.Response;
import com.priya.Quiz_App_SpringBoot.repository.QuestionRepo;
import com.priya.Quiz_App_SpringBoot.repository.QuizRepo;
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
    QuizRepo quizRepo;
    @Autowired
    QuestionRepo questionRepo;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionRepo.findRandomQuestionByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepo.save(quiz);

        return new ResponseEntity<>("Successfully Quiz created...", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        //creating obj of quiz and save there... but quiz id may exist or not and may also return null ...so we changed to Optional...
        Optional<Quiz> quiz = quizRepo.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions(); //first we used get() its because we used Optional in Quiz class type...
        //we get the quiz by ID and from that quiz we got the questions also...
        //now we can't return Question type questions directly...because it contains right_answer variable...
        //need to change it to QeustionWrapper type... and return it to users...
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        //converting Each qn...to QuestionWrapper type from Question...
        for(Question q : questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),
                                                     q.getQuestionTitle(),
                                                     q.getOption1(),
                                                     q.getOption2(),
                                                     q.getOption3(),
                                                     q.getOption4());

            questionsForUser.add(qw);
        }

        return new ResponseEntity(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizRepo.findById(id).get(); //instead optional put get()...
        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
