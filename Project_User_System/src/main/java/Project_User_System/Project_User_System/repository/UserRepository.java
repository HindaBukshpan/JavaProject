package Project_User_System.Project_User_System.repository;

import Project_User_System.Project_User_System.external_api.PollSystemService;
import Project_User_System.Project_User_System.model.User;
import Project_User_System.Project_User_System.model.UsersAnswer;
import Project_User_System.Project_User_System.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private static final String USER_TABLE = "users";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    PollSystemService pollSystemService;

    public String createUser(User user) {
        try {
            String sql = String.format("INSERT INTO %s (first_name, last_name, age, email, city, street, num_of_house) VALUES(?, ?, ?, ?, ?, ?, ?)", USER_TABLE);
            jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getAge(), user.getEmail(),
                    user.getCity(), user.getStreet(), user.getNumOfHouse());
            return "User created successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String updateUser(User user) {
        try {
            String sql = String.format("UPDATE %s SET first_name = ?, last_name = ?, age = ?, email = ?, city = ?, street = ?, num_of_house = ?, joining_date = ? WHERE id = ? ", USER_TABLE);
            jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getAge(), user.getEmail(),
                    user.getCity(), user.getStreet(), user.getNumOfHouse(), user.getJoiningDate(), user.getId());
            return "User updated successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String deleteUserById(Integer id) {
        String resultOfDeletedUsersVotes = pollSystemService.deleteUsersVotesByUserId(id);
        User userToDelete = getUserById(id);
        try {
            String sql = String.format("DELETE FROM %s WHERE id = ? ", USER_TABLE);
            jdbcTemplate.update(sql, id);
            return "User deleted successfully" + resultOfDeletedUsersVotes;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public List<User> getAllUsers() {
        try {
            String sql = String.format("SELECT * FROM %s", USER_TABLE);
            return jdbcTemplate.query(sql, new UserMapper());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public User getUserById(Integer id) {
        try {
            String sql = String.format("SELECT * FROM %s WHERE id = ? ", USER_TABLE);
            return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<UsersAnswer> getUserAnswersByUserId(Integer userId){
        System.out.println(3);
        return pollSystemService.getUserAnswersByUserId(userId);
    }

    public String getNumberOfAnsweredQuestionsByUserId(Integer userId){
        return pollSystemService.getNumberOfAnsweredQuestionsByUserId(userId);
    }


}