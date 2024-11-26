package Project_Poll_System.Project_Poll_System.controller;


import Project_Poll_System.Project_Poll_System.external_api.UserSystemService;
import Project_Poll_System.Project_Poll_System.model.Answer;
import Project_Poll_System.Project_Poll_System.model.Poll;
import Project_Poll_System.Project_Poll_System.model.UsersAnswer;
import Project_Poll_System.Project_Poll_System.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/polls")
public class PollController {

    @Autowired
    private PollService pollService;

    @Autowired
    UserSystemService userSystemService;

    @PostMapping(value = "/answer-question")
    public String answerTheQuestion(@RequestBody Answer answer){
        return pollService.answerTheQuestion(answer);}
    
    @DeleteMapping(value = "/delete-user", params = "id")
    public String deleteUsersVotesByUserId(@RequestParam("id") Integer id){
        return pollService.deleteUsersVotesByUserId(id);}

    @GetMapping(value = "/get-users-answers", params = "user-id")
    public List<UsersAnswer> getUserAnswersByUserId(@RequestParam("user_id") Integer userId){
        return pollService.getUserAnswersByUserId(userId);}

    @GetMapping(value = "/count-of-answered-question", params = "user_id")
    public String getNumberOfAnsweredQuestionsByUserId(@RequestParam Integer id){
        return pollService.getNumberOfAnsweredQuestionsByUserId(id);}

    @GetMapping(value = "/get-count-for-each-option-of-question", params = "id")
    public List<Map<String, Object>> getAnswersCountForEachOptionByQuestionId(@RequestParam("id") Integer questionId){
        return pollService.getAnswersCountForEachOptionByQuestionId(questionId);}

    @GetMapping(value = "/get-all-questions-with-votes-count")
    public List<Map<String, Object>> getAllQuestionsWithVotesCount(){
        return pollService.getAllQuestionsWithVotesCount();}

    @PostMapping(value = "/add-question")
    public String addNewQuestion(@RequestBody Poll poll) {return pollService.addNewQuestion(poll);}

    @PutMapping(value = "/update-question")
    public String updateQuestion(@RequestBody Poll poll) {return pollService.updateQuestion(poll);}

    @DeleteMapping(value = "/delete-question", params = "id")
    public String deleteQuestionById(@RequestParam Integer id) {return pollService.deleteQuestionById(id);}

    @GetMapping("/get-all-questions")
    public List<Poll> getAllQuestions() {
        return pollService.getAllQuestions();}

    @GetMapping(value = "/get-question-by-id", params = "user_id")
    public Poll getQuestionById(@RequestParam("user_id") Integer userId){
        return pollService.getQuestionById(userId);}

}
