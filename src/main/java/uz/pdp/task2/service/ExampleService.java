package uz.pdp.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task2.entity.Example;
import uz.pdp.task2.entity.Task;
import uz.pdp.task2.payload.ApiResponse;
import uz.pdp.task2.payload.ExampleDto;
import uz.pdp.task2.repository.ExampleRepository;
import uz.pdp.task2.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {

    @Autowired
    ExampleRepository exampleRepository;

    @Autowired
    TaskRepository taskRepository;

    public ApiResponse addExample(ExampleDto exampleDto) {

        Example example=new Example();
        example.setText(exampleDto.getText());
        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
        if (!optionalTask.isPresent())
        return new ApiResponse("Task not found",false);
        example.setTask(optionalTask.get());
        exampleRepository.save(example);
        return new ApiResponse("Example saved",true);
    }

    public List<Example> getExamples() {
        List<Example> exampleList = exampleRepository.findAll();
        return exampleList;
    }

    public Example getExampleById(Integer id) {
        Optional<Example> optionalExample = exampleRepository.findById(id);
        return optionalExample.orElse(null);
    }

    public ApiResponse editExample(ExampleDto exampleDto, Integer id) {

        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (!optionalExample.isPresent())
        return new ApiResponse("Task not found",false);

        Example example = optionalExample.get();
        example.setText(exampleDto.getText());

        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
        if (!optionalTask.isPresent())
        return new ApiResponse("Task not found",false);
        example.setTask(optionalTask.get());
        exampleRepository.save(example);
        return new ApiResponse("example edited",true);
    }

    public ApiResponse deleteExample(Integer id) {
        try {
            exampleRepository.deleteById(id);
            return new ApiResponse("example deleted",true);
        }catch (Exception e){
        return new ApiResponse("example not deleted",false);
    }}
}
