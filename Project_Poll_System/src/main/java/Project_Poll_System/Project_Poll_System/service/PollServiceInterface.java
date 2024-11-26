package Project_Poll_System.Project_Poll_System.service;

import Project_Poll_System.Project_Poll_System.model.Answer;
import Project_Poll_System.Project_Poll_System.model.Poll;
import Project_Poll_System.Project_Poll_System.model.UsersAnswer;

import java.util.List;
import java.util.Map;

public interface PollServiceInterface {
    String addNewQuestion(Poll poll);

    String updateQuestion(Poll poll);

    String deleteQuestionById(Integer id);

    List<Poll> getAllQuestions();

    Poll getQuestionById(Integer id);

    String answerTheQuestion(Answer answer);

    String deleteUsersVotesByUserId(Integer userId);

    List<Map<String, Object>> getAllQuestionsWithVotesCount();

//    Integer isQuestion(Integer questionId);

    String getNumberOfAnsweredQuestionsByUserId(Integer userId);

    List<Map<String, Object>> getAnswersCountForEachOptionByQuestionId(Integer questionId);



        List<UsersAnswer> getUserAnswersByUserId(Integer userId);
}
