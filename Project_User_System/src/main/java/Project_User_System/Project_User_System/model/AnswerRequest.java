package Project_User_System.Project_User_System.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnswerRequest {

        private int id;
        @JsonProperty("user_id")
        private int userId;
        @JsonProperty("question_id")
        private int questionId;
        private String answer;

        public AnswerRequest(int id, int userId, int questionId, String answer) {
            this.id = id;
            this.userId = userId;
            this.questionId = questionId;
            this.answer = answer;
        }

        public AnswerRequest() {}

        public int getId() {return id;}

        public void setId(int id) {this.id = id;}

        public int getUserId() {return userId;}

        public void setUserId(int userId) {this.userId = userId;}

        public int getQuestionId() {return questionId;}

        public void setQuestionId(int questionId) {this.questionId = questionId;}

        public String getAnswer() {return answer;}

        public void setAnswer(String answer) {this.answer = answer;}
    }


