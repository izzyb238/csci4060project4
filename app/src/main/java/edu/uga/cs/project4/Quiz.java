package edu.uga.cs.project4;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;

/**
 * POJO class, Quiz has list of 6 questions
 * score, and date
 */
public class Quiz implements Serializable {
    private long id;
    private int score;
    private int questionsAnswered;
    private ArrayList<Question> questionsList;
    private int currentIndex;

    /** default constructor */
    public Quiz () {
        this.id = -1;
        this.score = 0;
        this.questionsAnswered = 0;
        this.questionsList = new ArrayList<>();
        this.currentIndex = 0;
        updateScore();
    }

    public Quiz (ArrayList<Question> questions) {
        this.id = -1;
        this.score = 0;
        this.questionsAnswered = 0;
        this.questionsList = questions;
        this.currentIndex = 0;
        updateScore();
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    public int getScore() {return score;}
    public int getQuestionsAnswered() {return questionsAnswered;}
    public void addQuestion (Question question) {questionsList.add(question);}
    public ArrayList<Question> getQuestions() {
        return questionsList;
    }
    public int getCurrentIndex() {return currentIndex;}
    public void setCurrentIndex(int currentIndex) {this.currentIndex = currentIndex;}
    public void updateScore() {
        int newAnswered = 0;
        int newScore = 0;

        for (Question q: questionsList) {
            if (q.isAnswered()) {
                newAnswered++;
            }
            newScore += q.getScore();
        }
        questionsAnswered = newAnswered;
        score = newScore;
    }
    public boolean isFinished() {
        return questionsAnswered == questionsList.size();
        //if the index of the question answered is the same as the number of question, return true
    }

    public int getMaxScore() {
        return questionsList.size();
    }
}
