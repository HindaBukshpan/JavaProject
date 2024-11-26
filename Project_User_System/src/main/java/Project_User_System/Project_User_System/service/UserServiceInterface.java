package Project_User_System.Project_User_System.service;

import Project_User_System.Project_User_System.model.AnswerRequest;
import Project_User_System.Project_User_System.model.User;
import Project_User_System.Project_User_System.model.UsersAnswer;

import java.util.List;

public interface UserServiceInterface {

     String createUser(User user);

     String updateUser(User user);

     String deleteUserById(Integer id);

     List<User> getAllUsers();

     User getUserById(Integer id);

     Integer isRegistered(Integer id);

     List<UsersAnswer> getUserAnswersByUserId(Integer userId);

     String getNumberOfAnsweredQuestionsByUserId(Integer userId);

}
