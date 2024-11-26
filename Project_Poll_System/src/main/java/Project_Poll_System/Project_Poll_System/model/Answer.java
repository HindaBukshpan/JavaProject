package Project_Poll_System.Project_Poll_System.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Answer {
    private Integer id;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("question_id")
    private int questionId;
    private String answer;

    public Answer(int id, int userId, int questionId, String answer) {
        this.id = id;
        this.userId = userId;
        this.questionId = questionId;
        this.answer = answer;
    }

    public Answer() {}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public Integer getUserId() {return userId;}

    public void setUserId(Integer userId) {this.userId = userId;}

    public Integer getQuestionId() {return questionId;}

    public void setQuestionId(Integer questionId) {this.questionId = questionId;}

    public String getAnswer() {return answer;}

    public void setAnswer(String answer) {this.answer = answer;}
}