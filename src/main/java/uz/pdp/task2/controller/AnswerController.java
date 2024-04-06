package uz.pdp.task2.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task2.entity.Answer;
import uz.pdp.task2.payload.AnswerDto;
import uz.pdp.task2.payload.ApiResponse;
import uz.pdp.task2.service.AnswerService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @PostMapping("/api/answer")
    public ResponseEntity<ApiResponse> addAnswer(@Valid @RequestBody AnswerDto answerDto){
        ApiResponse apiResponse = answerService.addAnswer(answerDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/api/answer")
    public ResponseEntity<List<Answer>>getAnswers(){
        List<Answer> answers = answerService.getAnswers();
        return ResponseEntity.ok(answers);
    }

    @GetMapping("/api/answer/{id}")
    public ResponseEntity<Answer>getCompany(@PathVariable Integer id){
        Answer answerById = answerService.getAnswerById(id);
        return ResponseEntity.ok(answerById);
    }

    @PutMapping("/api/answer/{id}")
    public ResponseEntity<ApiResponse> editAnswer(@Valid @RequestBody AnswerDto answerDto, @PathVariable Integer id){
        ApiResponse apiResponse = answerService.editAnswer(answerDto,id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/api/answer/{id}")
    public ResponseEntity<?>deleteAnswer(@PathVariable Integer id){
        ApiResponse apiResponse = answerService.deleteAnswer(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    // in case of:  "status": 400, OR  "error": "Bad Request"
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex){

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
         return errors;
    }
}
