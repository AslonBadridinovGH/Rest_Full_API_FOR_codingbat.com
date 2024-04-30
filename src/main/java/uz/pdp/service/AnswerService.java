package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Answer;
import uz.pdp.entity.Task;
import uz.pdp.entity.User;
import uz.pdp.payload.AnswerDto;
import uz.pdp.payload.ApiResponse;
import uz.pdp.repository.AnswerRepository;
import uz.pdp.repository.TaskRepository;
import uz.pdp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    public ApiResponse addAnswer(AnswerDto answerDto) {

         Answer answer=new Answer();
        answer.set_correct( answerDto.is_correct());
        answer.setText(answerDto.getText());

        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("Task was not found",false);
        answer.setTask(optionalTask.get());

        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent())
            return new ApiResponse("User was not found",false);
        answer.setUser(optionalUser.get());
        answerRepository.save(answer);
        return new ApiResponse("Answer was saved",true);
    }

    public List<Answer> getAnswers() {
        List<Answer> answerList = answerRepository.findAll();
        return answerList;
    }

    public Answer getAnswerById(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        return optionalAnswer.orElse(null);
    }

    public ApiResponse editAnswer(AnswerDto answerDto, Integer id) {

        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent())
            return new ApiResponse("Answer was not found",false);

         Answer answer = optionalAnswer.get();
        answer.setText(answerDto.getText());
        answer.set_correct(answerDto.is_correct());
        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("Task was not found",false);
        answer.setTask(optionalTask.get());
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent())
            return new ApiResponse("User was not found",false);
        answer.setUser(optionalUser.get());
        answerRepository.save(answer);
        return new ApiResponse("Answer was edited",true);
    }
    public ApiResponse deleteAnswer(Integer id) {
        try {
            answerRepository.deleteById(id);
            return new ApiResponse("Answer was deleted",true);
        }catch (Exception e){
            return new ApiResponse("Answer was not deleted",false);
    }
}
}
