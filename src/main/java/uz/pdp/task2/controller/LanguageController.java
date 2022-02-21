package uz.pdp.task2.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task2.entity.Language;
import uz.pdp.task2.payload.ApiResponse;
import uz.pdp.task2.payload.LanguageDto;
import uz.pdp.task2.service.LanguageService;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LanguageController {

    @Autowired
    LanguageService languageService;


    @PostMapping("/api/language")
    public ResponseEntity<ApiResponse> addLanguage(@Valid @RequestBody LanguageDto languageDto){
        ApiResponse apiResponse = languageService.addLanguage(languageDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/api/language")
    public ResponseEntity<List<Language>>getLanguages(){
        List<Language> languages = languageService.getLanguage();
        return ResponseEntity.ok(languages);
    }

    @GetMapping("/api/language/{id}")
    public ResponseEntity<Language>getExample(@PathVariable Integer id){
        Language languageById = languageService.getLanguageById(id);
        return ResponseEntity.ok(languageById);
    }

    @PutMapping("/api/language/{id}")
    public ResponseEntity<ApiResponse> editLanguage(@Valid @RequestBody LanguageDto languageDto, @PathVariable Integer id){
        ApiResponse apiResponse = languageService.editLanguage(languageDto,id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/api/language/{id}")
    public ResponseEntity<?>deleteLanguage(@PathVariable Integer id){
        ApiResponse apiResponse = languageService.deleteLanguage(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }




    // "status": 400, "error": "Bad Request" BÖLSA SHU YERGA TUSHADI
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
