package Project_User_System.Project_User_System.controller;


import Project_User_System.Project_User_System.external_api.PollSystemService;
import Project_User_System.Project_User_System.model.AnswerRequest;
import Project_User_System.Project_User_System.model.User;
import Project_User_System.Project_User_System.model.UsersAnswer;
import Project_User_System.Project_User_System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    PollSystemService pollSystemService;

    @PostMapping(value = "/create")
    public String createUser(@RequestBody User user) {return userService.createUser(user);}

    @PutMapping(value = "/update")
    public String updateUser(@RequestBody User user) {return userService.updateUser(user);}

    @DeleteMapping(value = "/delete", params = "id")
    public String deleteUser(@RequestParam Integer id) {
//        System.out.println(id);
//        String resultOfDeletedUsersVotes = pollSystemService.deleteUsersVotesByUserId(id);
//        System.out.println(resultOfDeletedUsersVotes);
        return userService.deleteUserById(id);}

    @GetMapping()
    public List<User> gatAllUsers() {return userService.getAllUsers();}

    @GetMapping(value = "/get-user-by-id", params = "id")
    public User getUserById(@RequestParam Integer id){return userService.getUserById(id);}

    @PostMapping(value = "/answer-question")
    public String answerTheQuestion(@RequestBody AnswerRequest answerRequest){
        return pollSystemService.answerTheQuestion(answerRequest);}

    @GetMapping(value = "", params = "id")
    public Integer isRegistered(@RequestParam Integer id){
        return userService.isRegistered(id);}

    @GetMapping(value = "/get-users-answers", params = "user_id")
    public List<UsersAnswer> getUserAnswersByUserId(@RequestParam("user_id") Integer userId){
        System.out.println(1);
        return userService.getUserAnswersByUserId(userId);}

    @GetMapping(value = "/count-of-answered-question", params = "user-id")
    public String getNumberOfAnsweredQuestionsByUserId(@RequestParam("user_id") Integer userId){
        return userService.getNumberOfAnsweredQuestionsByUserId(userId);}

}
