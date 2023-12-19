package com.telusko.QuizApplication.service;


import com.telusko.QuizApplication.model.Question;
import com.telusko.QuizApplication.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public String addQuestion(Question question) {
        try{
            questionDao.save(question);
            return "Question Added Successfully";
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    public String updateQuestion(int id, Question updatedQuestion) {
        Question existingQuestion = questionDao.findById(id).get();

        try{
            // Update only the fields that are not null in the updatedQuestion
            if (updatedQuestion.getQuestionTitle() != null) {
                existingQuestion.setQuestionTitle(updatedQuestion.getQuestionTitle());
            }
            if (updatedQuestion.getOption1() != null) {
                existingQuestion.setOption1(updatedQuestion.getOption1());
            }
            if (updatedQuestion.getOption2() != null) {
                existingQuestion.setOption2(updatedQuestion.getOption2());
            }
            if (updatedQuestion.getOption3() != null) {
                existingQuestion.setOption3(updatedQuestion.getOption3());
            }
            if (updatedQuestion.getOption4() != null) {
                existingQuestion.setOption4(updatedQuestion.getOption4());
            }
            if (updatedQuestion.getRightAnswer() != null) {
                existingQuestion.setRightAnswer(updatedQuestion.getRightAnswer());
            }
            if (updatedQuestion.getDifficultyLevel() != null) {
                existingQuestion.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
            }
            if (updatedQuestion.getCategory() != null) {
                existingQuestion.setCategory(updatedQuestion.getCategory());
            }

            // Save the updated question to the database
            questionDao.save(existingQuestion);
            return "Success: Question updated successfully.";
        }catch(Exception e){
            return "Error: Question not found with id " + id;
        }
    }


    public void deleteQuestion(int id) {
        try{
            questionDao.deleteById(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
