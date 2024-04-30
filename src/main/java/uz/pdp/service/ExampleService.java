package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Example;
import uz.pdp.entity.Task;
import uz.pdp.payload.ApiResponse;
import uz.pdp.payload.ExampleDto;
import uz.pdp.repository.ExampleRepository;
import uz.pdp.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {

    @Autowired
    ExampleRepository exampleRepository;

    @Autowired
    TaskRepository taskRepository;

    public ApiResponse addExample(ExampleDto exampleDto) {

          Example example = new Example();
        example.setText(exampleDto.getText());
        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
        if (!optionalTask.isPresent()){
            return new ApiResponse("Task was not found",false);
        }
        example.setTask(optionalTask.get());
        exampleRepository.save(example);
        return new ApiResponse("Example was saved",true);
    }

    public List<Example> getExamples() {
        List<Example> exampleList = exampleRepository.findAll();
        return exampleList;
    }

    public Example getExampleById(Integer id) {
        Optional<Example> optionalExample = exampleRepository.findById(id);
        return optionalExample.orElse(new Example());
    }

    public ApiResponse editExample(ExampleDto exampleDto, Integer id) {

        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (!optionalExample.isPresent()){
            return new ApiResponse("Task was not found",false);
        }

         Example example = optionalExample.get();
        example.setText(exampleDto.getText());

        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
        if (!optionalTask.isPresent()){
            return new ApiResponse("Task was not found",false);
        }
        example.setTask(optionalTask.get());
        exampleRepository.save(example);
        return new ApiResponse("example was edited",true);
    }

    public ApiResponse deleteExample(Integer id) {
        try {
            exampleRepository.deleteById(id);
            return new ApiResponse("example was deleted",true);
        }catch (Exception e){
        return new ApiResponse("example was not deleted",false);
    }}
}
