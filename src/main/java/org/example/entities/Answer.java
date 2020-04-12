package org.example.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Answer")

public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private int answerId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="question_id", nullable=false)
    private Question question;

    @Column(name="answer")
    private String answer;

    @Column(name="is_right")
    private boolean isRight;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "answer")
    private List<Result> results;

    public Answer() {
    }

    public Answer(Question question, String answer, boolean isRight) {
        this.question = question;
        this.answer = answer;
        this.isRight = isRight;
    }

    public Answer(int answer_id, Question question, String answer, boolean isRight) {
        this.answerId = answer_id;
        this.question = question;
        this.answer = answer;
        this.isRight = isRight;
    }

    public int getAnswerId() {
        return answerId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
