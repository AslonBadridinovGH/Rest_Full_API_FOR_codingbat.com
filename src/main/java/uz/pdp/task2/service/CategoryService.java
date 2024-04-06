package uz.pdp.task2.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task2.entity.Category;
import uz.pdp.task2.payload.ApiResponse;
import uz.pdp.task2.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    public ApiResponse addCategory(Category category) {

        categoryRepository.save(category);
        return new ApiResponse("Category was saved",true);
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElseGet(Category::new);
    }

    public ApiResponse editCategory(Category category, Integer id) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            return new ApiResponse("Category was not found",false);
        }
        Category category1 = optionalCategory.get();
        category1.setName(category.getName());
        category1.setDescription(category.getDescription());
        categoryRepository.save(category1);
        return new ApiResponse("Category was edited",true);
    }

    public ApiResponse deleteCategory(Integer id) {
       try {
           categoryRepository.deleteById(id);
           return new ApiResponse("Category was deleted",true);
       }catch (Exception e){
           return new ApiResponse("Category was not deleted",false);
       }
    }
    
}
