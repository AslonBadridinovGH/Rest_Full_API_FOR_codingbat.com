package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Language;
import uz.pdp.entity.Task;
import uz.pdp.payload.ApiResponse;
import uz.pdp.payload.TaskDto;
import uz.pdp.repository.LanguageRepository;
import uz.pdp.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    LanguageRepository languageRepository;

    public ApiResponse addTask(TaskDto taskDto) {

        Task task=new Task();
        task.setHasStar(taskDto.isHasStar());
        task.setHint(taskDto.getHint());
        task.setName(taskDto.getName());
        task.setMethod(taskDto.getMethod());
        task.setSolution(taskDto.getSolution());
        task.setText(taskDto.getText());

        Optional<Language> optionalLanguage = languageRepository.findById(taskDto.getLanguageId());
        if (!optionalLanguage.isPresent()) {
            return new ApiResponse("Language was not found",false);
        }
        task.setLanguage(optionalLanguage.get());
        taskRepository.save(task);
        return new ApiResponse("Task was saved",true);
    }

    public List<Task> getTasks() {
        List<Task> taskList = taskRepository.findAll();
        return taskList;
    }

    public Task getTaskById(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElseGet(Task::new);
    }

    public ApiResponse editTask(TaskDto taskDto, Integer id) {

        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return new ApiResponse("Task wasn't found",false);
        }

        Task task = optionalTask.get();
        task.setHasStar(taskDto.isHasStar());
        task.setHint(taskDto.getHint());
        task.setName(taskDto.getName());
        task.setMethod(taskDto.getMethod());
        task.setSolution(taskDto.getSolution());
        task.setText(taskDto.getText());

        Optional<Language> optionalLanguage = languageRepository.findById(taskDto.getLanguageId());
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Language wasn't found",false);
        task.setLanguage(optionalLanguage.get());
        taskRepository.save(task);
        return new ApiResponse("Task was edited",true);
    }

    public ApiResponse deleteTask(Integer id) {

        try {
            languageRepository.deleteById(id);
            return new ApiResponse("Task was deleted",true);
        }catch (Exception e){
            return new ApiResponse("Task wasn't deleted",false);
        }
}
}
