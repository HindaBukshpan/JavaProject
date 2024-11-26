package Project_Poll_System.Project_Poll_System.service;

import Project_Poll_System.Project_Poll_System.external_api.UserSystemService;
import Project_Poll_System.Project_Poll_System.model.Answer;
import Project_Poll_System.Project_Poll_System.model.Poll;
import Project_Poll_System.Project_Poll_System.model.UsersAnswer;
import Project_Poll_System.Project_Poll_System.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PollService implements PollServiceInterface{
    @Autowired
    private PollRepository pollRepository;

    @Autowired
    UserSystemService userSystemService;

    @Override
    public String addNewQuestion(Poll poll) {
        if(poll.getTitle() == null || poll.getOption1() == null ||  poll.getOption2() == null
                || poll.getOption3() == null ||  poll.getOption4() == null) {
            return "Fill in all the required details (Title, Option 1, Option 2, Option 3 and Option 4)";
        }
        return pollRepository.addNewQuestion(poll);
    }

    @Override
    public String updateQuestion(Poll poll) {
        Poll questionBeforeUpdate = pollRepository.getQuestionById(poll.getId());
        if (questionBeforeUpdate == null) {
            System.out.println(pollRepository.getQuestionById(poll.getId()));
            return "Can't update a question that doesn't exist";
        }
        if (poll.getTitle() == null) {
            poll.setTitle(questionBeforeUpdate.getTitle());
        }
        if (poll.getOption1() == null) {
            poll.setOption1(questionBeforeUpdate.getOption1());
        }
        if (poll.getOption2() == null) {
            poll.setOption2(questionBeforeUpdate.getOption2());
        }
        if (poll.getOption3() == null) {
            poll.setOption3(questionBeforeUpdate.getOption3());
        }
        if (poll.getOption4() == null) {
            poll.setOption4(questionBeforeUpdate.getOption4());
        }

        return pollRepository.updateQuestion(poll);
    }

    @Override
    public String deleteQuestionById(Integer id) {
        Poll questionBeforeDelete = pollRepository.getQuestionById(id);
        if (id ==null){
            return "Can't delete a question without an id";
        }
        if(questionBeforeDelete == null){
            return "Can't delete a question that doesn't exist";
        }
        return pollRepository.deleteQuestionById(id);
    }

    @Override
    public List<Poll> getAllQuestions() {
        return pollRepository.getAllQuestions();
    }

    @Override
    public Poll getQuestionById(Integer id) {
        if (id == null){
            System.out.println("can't find a question that doesn't exist");
            return null;
        }
        return pollRepository.getQuestionById(id);
    }

    @Override
    public String answerTheQuestion(Answer answer) {
        Integer response = userSystemService.isRegistered(answer.getUserId());
        Poll question = getQuestionById(answer.getQuestionId());
        if (response == 1) {
            if (question.getId() != answer.getQuestionId() ){
                return "Can answer only question that exist";
            }
            if (answer.getUserId() == null || answer.getQuestionId() == null
                    || answer.getAnswer() == null) {
                return "Fill in all the required details (User id, Question id, Answer)";
                }
            if (!answer.getAnswer().equals(question.getOption1()) && !answer.getAnswer().equals(question.getOption2())
            && !answer.getAnswer().equals(question.getOption3()) && !answer.getAnswer().equals(question.getOption4())){
                return "Enter a valid answer";
            }
            return pollRepository.answerTheQuestion(answer);
        }
        return "You need to be registered to be able to answer on questions, Please register";
    }

    @Override
    public String deleteUsersVotesByUserId(Integer id){
        if (id == null){
            return ", there were not votes for this user to delete";
        }
            return pollRepository.deleteUsersVotesByUserId(id);
    }

    @Override
    public List<Map<String, Object>> getAllQuestionsWithVotesCount() {
        return pollRepository.getAllQuestionsWithVotesCount();
    }

//    @Override
//    public Integer isQuestion(Integer id){
//        Poll result = pollRepository.getQuestionById(id);
//        if (result != null){
//            return 1;
//        }
//        return 0;
//    }

    @Override
    public List<UsersAnswer> getUserAnswersByUserId(@RequestParam Integer userId) {
        List<UsersAnswer> listOfAnswers = pollRepository.getAnswersByUserId(userId);
        if (listOfAnswers.isEmpty()) {
            String message = "No answers found for user_id: " + userId;
            System.out.println(message);
//            throw new IllegalArgumentException(message);
            return new ArrayList<>();
    }
        return pollRepository.getAnswersByUserId(userId);
    }

    @Override
    public String getNumberOfAnsweredQuestionsByUserId(Integer id){
        if (id == null){
            return "No answers found for user_id: " + id;
        }
        return pollRepository.getNumberOfAnsweredQuestionsByUserId(id);
    }

    @Override
    public List<Map<String, Object>> getAnswersCountForEachOptionByQuestionId(Integer questionId){
        return pollRepository.getAnswersCountForEachOptionByQuestionId(questionId);
    }




}
