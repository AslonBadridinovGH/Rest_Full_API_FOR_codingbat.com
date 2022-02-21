package uz.pdp.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task2.entity.Answer;
import uz.pdp.task2.entity.Task;
import uz.pdp.task2.entity.User;
import uz.pdp.task2.payload.AnswerDto;
import uz.pdp.task2.payload.ApiResponse;
import uz.pdp.task2.repository.AnswerRepository;
import uz.pdp.task2.repository.TaskRepository;
import uz.pdp.task2.repository.UserRepository;

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
            return new ApiResponse("Task not found",false);
        answer.setTask(optionalTask.get());

        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent())
            return new ApiResponse("User not found",false);
        answer.setUser(optionalUser.get());
        answerRepository.save(answer);
        return new ApiResponse("Answer saved",true);
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
            return new ApiResponse("Answer not found",false);
        Answer answer = optionalAnswer.get();
        answer.setText(answerDto.getText());
        answer.set_correct(answerDto.is_correct());
        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("Task not found",false);
        answer.setTask(optionalTask.get());
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent())
            return new ApiResponse("User not found",false);
        answer.setUser(optionalUser.get());
        answerRepository.save(answer);
        return new ApiResponse("Answer edited",true);
    }
    public ApiResponse deleteAnswer(Integer id) {
        try {
            answerRepository.deleteById(id);
            return new ApiResponse("Answer deleted",true);
        }catch (Exception e){
            return new ApiResponse("Answer not deleted",false);
    }
}
}
