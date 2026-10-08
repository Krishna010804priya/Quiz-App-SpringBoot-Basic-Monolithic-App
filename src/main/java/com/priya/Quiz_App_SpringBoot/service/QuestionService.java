package com.priya.Quiz_App_SpringBoot.service;

import com.priya.Quiz_App_SpringBoot.model.Question;
import com.priya.Quiz_App_SpringBoot.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo questionRepo;

    public ResponseEntity<List<Question>> getAllQuestions() {
        //exception handling request...
        try{
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionRepo.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
//         try{
//             questionRepo.save(question);
//             return new ResponseEntity<>("Added...", HttpStatus.CREATED);
//         }catch (Exception e){
//             e.printStackTrace();
//         }
//         return new ResponseEntity<>("Not Added!!!", HttpStatus.BAD_REQUEST);
        questionRepo.save(question);
        return new ResponseEntity<>("Added...", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateQuestion(Question question){
//        try{
//            questionRepo.save(question);
//            return new ResponseEntity<>("Updated...", HttpStatus.CREATED);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>("Not Updated!!!", HttpStatus.BAD_REQUEST);
        questionRepo.save(question);
        return new ResponseEntity<>("Updated...", HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteQuestionById(Integer id) {
//        try{
//            questionRepo.deleteById(id);
//            return new ResponseEntity<>("Deleted...", HttpStatus.OK);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>("Not Deleted!!!", HttpStatus.BAD_REQUEST);
         questionRepo.deleteById(id);
         return new ResponseEntity<>("Deleted...", HttpStatus.OK);
    }


}
