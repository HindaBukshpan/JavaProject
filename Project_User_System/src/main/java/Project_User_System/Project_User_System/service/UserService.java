package Project_User_System.Project_User_System.service;

import Project_User_System.Project_User_System.external_api.PollSystemService;
import Project_User_System.Project_User_System.model.User;
import Project_User_System.Project_User_System.model.UsersAnswer;
import Project_User_System.Project_User_System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PollSystemService pollSystemService;

    @Override
    public String createUser(User user) {
        if(user.getFirstName() == null || user.getLastName() == null
        || user.getCity() == null || user.getEmail() == null) {
            return "Fill in all the required details (First name, Last name, City and Email)";
        }
        if (user.getAge() == null) {
            user.setAge(0);
        }
        if (user.getNumOfHouse() == null) {
            user.setNumOfHouse(0);
        }
        if (user.getStreet() == null){
            user.setStreet("");
        }

        return userRepository.createUser(user);
    }

    @Override
    public String updateUser(User user) {
        User userBeforeUpdate = userRepository.getUserById(user.getId());
        if (userBeforeUpdate == null) {
            System.out.println(userRepository.getUserById(user.getId()));
            return "Can't update a user that doesn't exist";
        }
        if (user.getFirstName() == null) {
          user.setFirstName(userBeforeUpdate.getFirstName());
        }
        if (user.getLastName() == null) {
            user.setLastName(userBeforeUpdate.getLastName());
        }
        if (user.getEmail() == null) {
            user.setEmail(userBeforeUpdate.getEmail());
        }
        if (user.getAge() == null) {
           user.setAge(userBeforeUpdate.getAge());
        }
        if (user.getCity() == null) {
            user.setCity(userBeforeUpdate.getCity());
        }
        if (user.getStreet() == null) {
            user.setStreet(userBeforeUpdate.getStreet());
        }
        if (user.getNumOfHouse() == null) {
            user.setNumOfHouse(userBeforeUpdate.getNumOfHouse());
        }
        if (user.getJoiningDate() == null) {
            user.setJoiningDate(userBeforeUpdate.getJoiningDate());
        }
                return userRepository.updateUser(user);
    }

    @Override
    public String deleteUserById(Integer id) {
        User userBeforeDelete = userRepository.getUserById(id);
//        String resultOfDeletedUsersVotes = pollSystemService.deleteUsersVotesByUserId(userBeforeDelete.getId());
        if (id ==null){
            return "Can't delete a user without an id";
        }
        if(userBeforeDelete == null){
            return "Can't delete a user that doesn't exist";
        }
        return userRepository.deleteUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getUserById(Integer id) {
        if (id == null){
            System.out.println("can't find a user that doesn't exist");
            return null;
        }
        return userRepository.getUserById(id);
    }

    @Override
    public Integer isRegistered(Integer id){
        User response = userRepository.getUserById(id);
        if (response != null){
            return 1;
        }
        return 0;
    }

    @Override
    public List<UsersAnswer> getUserAnswersByUserId(Integer userId){
        if(getUserById(userId) == null){
                String message = "No found a user with this id";
                System.out.println(message);
//                throw new RuntimeException(message);
                return new ArrayList<>();
        }
        return userRepository.getUserAnswersByUserId(userId);
    }

    public String getNumberOfAnsweredQuestionsByUserId(Integer userId){
        System.out.println(2);
        if(getUserById(userId) == null){
            return "No found a user with this id";
        }
        return userRepository.getNumberOfAnsweredQuestionsByUserId(userId);
    }

}
