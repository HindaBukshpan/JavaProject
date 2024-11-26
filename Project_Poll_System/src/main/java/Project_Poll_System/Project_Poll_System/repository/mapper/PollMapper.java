package Project_Poll_System.Project_Poll_System.repository.mapper;

import Project_Poll_System.Project_Poll_System.model.Poll;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PollMapper implements RowMapper<Poll> {
    @Override
    public Poll mapRow(ResultSet rs, int rowNum) throws SQLException {
        Poll poll = new Poll();
        poll.setId(rs.getInt("id"));
        poll.setTitle(rs.getString("title"));
        poll.setOption1(rs.getString("option1"));
        poll.setOption2(rs.getString("option2"));
        poll.setOption3(rs.getString("option3"));
        poll.setOption4(rs.getString("option4"));

        return poll;
    }
}
