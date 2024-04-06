package uz.pdp.task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task2.entity.Example;
import uz.pdp.task2.payload.ApiResponse;
import uz.pdp.task2.payload.ExampleDto;
import uz.pdp.task2.service.ExampleService;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ExampleController {

    @Autowired
    ExampleService exampleService;

    @PostMapping("/api/example")
    public ResponseEntity<ApiResponse> addExample(@RequestBody ExampleDto exampleDto){
        ApiResponse apiResponse = exampleService.addExample(exampleDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/api/example")
    public ResponseEntity<List<Example>>getExamples(){
        List<Example> examples = exampleService.getExamples();
        return ResponseEntity.ok(examples);
    }

    @GetMapping("/api/example/{id}")
    public ResponseEntity<Example>getExample(@PathVariable Integer id){
        Example exampleById = exampleService.getExampleById(id);
        return ResponseEntity.ok(exampleById);
    }

    @PutMapping("/api/example/{id}")
    public ResponseEntity<ApiResponse> editExample(@RequestBody ExampleDto exampleDto, @PathVariable Integer id){
        ApiResponse apiResponse = exampleService.editExample(exampleDto,id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/api/example/{id}")
    public ResponseEntity<?>deleteExample(@PathVariable Integer id){
        ApiResponse apiResponse = exampleService.deleteExample(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

}
