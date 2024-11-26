package Project_User_System.Project_User_System.external_api;

import Project_User_System.Project_User_System.model.AnswerRequest;
import Project_User_System.Project_User_System.model.UsersAnswer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "externalapi",
        url = "${externalApi.polls.url}"
)

public interface PollSystemService {
    @PostMapping(value = "/answer-question")
    String answerTheQuestion(AnswerRequest answerRequest);

    @DeleteMapping(value = "/delete-user", params = "id")
    String deleteUsersVotesByUserId(@RequestParam("id") Integer id);

    @GetMapping(value = "/get-users-answers", params = "user-id")
    List<UsersAnswer> getUserAnswersByUserId(@RequestParam("user_id") Integer userId);

    @GetMapping(value = "/count-of-answered-question", params = "user-id")
    String getNumberOfAnsweredQuestionsByUserId(@RequestParam("user_id") Integer userId);


}
