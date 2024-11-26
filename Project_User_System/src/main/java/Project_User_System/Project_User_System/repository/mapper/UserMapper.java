package Project_User_System.Project_User_System.repository.mapper;

import Project_User_System.Project_User_System.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setAge(rs.getInt("age"));
        user.setEmail(rs.getString("email"));
        user.setCity(rs.getString("city"));
        user.setStreet(rs.getString("street"));
        user.setNumOfHouse(rs.getInt("num_of_house"));
        user.setJoiningDate(rs.getDate("joining_date").toLocalDate());

        return user;
    }
}
