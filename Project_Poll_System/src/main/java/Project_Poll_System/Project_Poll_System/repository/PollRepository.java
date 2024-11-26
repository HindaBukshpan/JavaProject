package Project_Poll_System.Project_Poll_System.repository;

import Project_Poll_System.Project_Poll_System.model.Answer;
import Project_Poll_System.Project_Poll_System.model.Poll;
import Project_Poll_System.Project_Poll_System.model.UsersAnswer;
import Project_Poll_System.Project_Poll_System.repository.mapper.PollMapper;
import Project_Poll_System.Project_Poll_System.repository.mapper.UsersAnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class PollRepository {

    private static final String QUESTION_TABLE = "questions";
    private static final String ANSWER_TABLE = "answers";


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public String addNewQuestion(Poll poll) {
        try {
            String sql = String.format("INSERT INTO %s (title, option1, option2, option3, option4) VALUES(?, ?, ?, ?, ?)", QUESTION_TABLE);
            jdbcTemplate.update(sql, poll.getTitle(), poll.getOption1(), poll.getOption2(), poll.getOption3(), poll.getOption4());
            return "Question created successfully";
        } catch (Exception e){
            return e.getMessage();
        }
    }

    public String updateQuestion(Poll poll) {
        try {
            String sql = String.format("UPDATE %s SET title = ?, option1 = ?, option2 = ?, option3 = ?, option4 = ? WHERE id = ? ", QUESTION_TABLE);
            jdbcTemplate.update(sql, poll.getTitle(), poll.getOption1(), poll.getOption2(), poll.getOption3(), poll.getOption4(), poll.getId());
            return "Question updated successfully";
        } catch (Exception e){
            return e.getMessage();
        }
    }

    public String deleteQuestionById(Integer id) {
        try {
            String sql = String.format("DELETE FROM %s WHERE id = ? ", QUESTION_TABLE);
            jdbcTemplate.update(sql, id);
            return "Question deleted successfully";
        } catch (Exception e){
            return e.getMessage();
        }
    }

    public List<Poll> getAllQuestions() {
        try {
            String sql = String.format("SELECT * FROM %s", QUESTION_TABLE);
            return jdbcTemplate.query(sql, new PollMapper());
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Poll getQuestionById(Integer id) {
        try {
            String sql = String.format("SELECT * FROM %s WHERE id = ? ", QUESTION_TABLE);
            return jdbcTemplate.queryForObject(sql, new PollMapper(), id);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String answerTheQuestion(Answer answer){
        try {
            String sql = String.format("INSERT INTO %s (user_id, question_id, answer) VALUES(?, ?, ?)", ANSWER_TABLE);
            jdbcTemplate.update(sql, answer.getUserId(), answer.getQuestionId(), answer.getAnswer());
            return "You answered successfully to this question";
        }catch (DataIntegrityViolationException e){
            return "You already answered this question";
        }
    }

    public String deleteUsersVotesByUserId(Integer userId){
        try {
            String sql = String.format("DELETE FROM %s WHERE user_id = ? ", ANSWER_TABLE);//????
            jdbcTemplate.update(sql,userId);
            return " with all his votes";
        } catch (Exception e){
            return null;
        }
    }


//    public List<Map<String, Object>> getAllQuestionsWithVotesCount(){
//        try {
//            String sql = String.format(
//                    "SELECT qt.title question, qt.id, at.answer, COUNT(*) AS count " +
//                            "FROM %s at RIGHT JOIN %s qt ON at.question_id = qt.id " +
//                            "GROUP BY at.question_id, at.answer, qt.title " +
//                            "ORDER BY at.question_id, at.answer", ANSWER_TABLE, QUESTION_TABLE
//            );
//            return jdbcTemplate.queryForList(sql);
//        } catch (Exception e){
//            e.printStackTrace();
//            return Collections.emptyList();
//        }
//    }

    public List<Map<String, Object>> getAllQuestionsWithVotesCount() {
        try {
            String sql = String.format(
                    "SELECT q.title, q.option1 AS answer, COUNT(a.answer) as count_answers " +
                            "FROM %s q " +
                            "LEFT JOIN %s a ON q.option1 = a.answer " +
                            "GROUP BY q.title, q.option1 " +
                            "UNION ALL " +
                            "SELECT q.title, q.option2 AS answer, COUNT(a.answer) as count_answers " +
                            "FROM %s q " +
                            "LEFT JOIN %s a ON q.option2 = a.answer " +
                            "GROUP BY q.title, q.option2 " +
                            "UNION ALL " +
                            "SELECT q.title, q.option3 AS answer, COUNT(a.answer) as count_answers " +
                            "FROM %s q " +
                            "LEFT JOIN %s a ON q.option3 = a.answer " +
                            "GROUP BY q.title, q.option3 " +
                            "UNION ALL " +
                            "SELECT q.title, q.option4 AS answer, COUNT(a.answer) as count_answers " +
                            "FROM %s q " +
                            "LEFT JOIN %s a ON q.option4 = a.answer " +
                            "GROUP BY q.title, q.option4",
                            QUESTION_TABLE, ANSWER_TABLE,
                            QUESTION_TABLE, ANSWER_TABLE,
                            QUESTION_TABLE, ANSWER_TABLE,
                            QUESTION_TABLE, ANSWER_TABLE);

            return jdbcTemplate.queryForList(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }




    public List<UsersAnswer> getAnswersByUserId(Integer userId) {
        System.out.println(5);
        try {
                String sql = String.format("SELECT question_id, answer FROM %s WHERE user_id = ?", ANSWER_TABLE);
                return jdbcTemplate.query(sql, new UsersAnswerMapper(), userId);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public String getNumberOfAnsweredQuestionsByUserId(Integer userId){
        try {
            String sql = String.format("SELECT COUNT(*) AS users_answers FROM %s WHERE user_id = ? ", ANSWER_TABLE);
            Integer response = jdbcTemplate.queryForObject(sql,Integer.class, userId);
            return "The amount of answered question for user with id: " + userId + " is " + response;
        } catch (Exception e){
            return null;
        }
    }

    public List<Map<String, Object>> getAnswersCountForEachOptionByQuestionId(Integer questionId){
    try {
        String sql = String.format(
                "SELECT q.title, q.option1 AS answer, COUNT(a.answer) AS count_of_answers " +
                        "FROM %s q " +
                        "LEFT JOIN %s a ON q.option1 = a.answer AND q.id = ? " +
                        "WHERE q.id = ? " +
                        "GROUP BY q.title, q.option1 " +
                        "UNION ALL " +
                        "SELECT q.title, q.option2 AS answer, COUNT(a.answer) AS count_of_answers " +
                        "FROM %s q " +
                        "LEFT JOIN %s a ON q.option2 = a.answer AND q.id = ? " +
                        "WHERE q.id = ? " +
                        "GROUP BY q.title, q.option2 " +
                        "UNION ALL " +
                        "SELECT q.title, q.option3 AS answer, COUNT(a.answer) AS count_of_answers " +
                        "FROM %s q " +
                        "LEFT JOIN %s a ON q.option3 = a.answer AND q.id = ? " +
                        "WHERE q.id = ? " +
                        "GROUP BY q.title, q.option3 " +
                        "UNION ALL " +
                        "SELECT q.title, q.option4 AS answer, COUNT(a.answer) AS count_of_answers " +
                        "FROM %s q " +
                        "LEFT JOIN %s a ON q.option4 = a.answer AND q.id = ? " +
                        "WHERE q.id = ? " +
                        "GROUP BY q.title, q.option4",
                QUESTION_TABLE, ANSWER_TABLE,
                QUESTION_TABLE, ANSWER_TABLE,
                QUESTION_TABLE, ANSWER_TABLE,
                QUESTION_TABLE, ANSWER_TABLE);

        return jdbcTemplate.queryForList(sql, questionId, questionId, questionId, questionId, questionId, questionId, questionId, questionId);
    } catch (Exception e) {
        e.printStackTrace();
        return Collections.emptyList();
    }
}


    public String getAnswersCountForEachQuestionByQuestionId(Integer questionId){
        try {
            String sql = String.format("" +
                    "select a.question_id, q.title,  a.answer, count(a.answer) as count\n" +
                    "from answers a inner join questions q\n" +
                    "on q.id = a.question_id\n" +
                    "group by question_id, answer, title" +
                    "SELECT COUNT(*) AS questions_answers FROM %s WHERE question_id = ? ", ANSWER_TABLE);
            Integer response = jdbcTemplate.queryForObject(sql,Integer.class, questionId);
            if (response == 0){
                return "There is no answers for this question";
            }
            return "The amount of answers  for question with id: " + questionId + " is " + response;
        } catch (Exception e){
            return null;
        }
    }




}
