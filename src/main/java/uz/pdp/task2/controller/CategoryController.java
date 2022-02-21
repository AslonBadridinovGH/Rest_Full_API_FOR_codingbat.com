package uz.pdp.task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task2.entity.Category;
import uz.pdp.task2.payload.ApiResponse;
import uz.pdp.task2.service.CategoryService;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/api/category")
    public ResponseEntity<ApiResponse> addCategory(@Valid @RequestBody Category category){
        ApiResponse apiResponse = categoryService.addCategory(category);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/api/category")
    public ResponseEntity<List<Category>>getCategories(){
        List<Category> categories = categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/api/category/{id}")
    public ResponseEntity<Category>getCategory(@PathVariable Integer id){
        Category categoryById = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryById);
    }

    @PutMapping("/api/category/{id}")
    public ResponseEntity<ApiResponse> editCategory(@Valid @RequestBody Category category,@PathVariable Integer id){
        ApiResponse apiResponse = categoryService.editCategory(category,id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/api/category/{id}")
    public ResponseEntity<?>deleteCategory(@PathVariable Integer id){
        ApiResponse apiResponse = categoryService.deleteCategory(id);
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
