package Project_Poll_System.Project_Poll_System.repository.mapper;

import Project_Poll_System.Project_Poll_System.model.UsersAnswer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersAnswerMapper implements RowMapper<UsersAnswer> {
    @Override
    public UsersAnswer mapRow(ResultSet rs, int rowNum) throws SQLException {
        UsersAnswer usersAnswer = new UsersAnswer();
        usersAnswer.setAnswer(rs.getString("answer"));
        usersAnswer.setQuestionId(rs.getInt("question_id"));
        return usersAnswer;
    }
}
