package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Category;
import uz.pdp.entity.Language;
import uz.pdp.payload.ApiResponse;
import uz.pdp.payload.LanguageDto;
import uz.pdp.repository.CategoryRepository;
import uz.pdp.repository.LanguageRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    CategoryRepository categoryRepository;


    public ApiResponse addLanguage(LanguageDto languageDto) {

         Language language=new Language();
        language.setName(languageDto.getName());

        Set<Category>categories=new HashSet<>();

        List<Integer> categoryIds = languageDto.getCategoryIds();
        for (Integer categoryId : categoryIds) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (!optionalCategory.isPresent()){
            return new ApiResponse(categoryId+"  CategoryId was not found",false);
        }
            categories.add(optionalCategory.get());
        }
        language.setCategory(categories);
        languageRepository.save(language);
        return new ApiResponse("Language saved",true);
    }

    public List<Language> getLanguage() {
        return languageRepository.findAll();
    }

    public Language getLanguageById(Integer id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return optionalLanguage.orElse(null);
    }

    public ApiResponse editLanguage(LanguageDto languageDto, Integer id) {

        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent()){
            return new ApiResponse("Language was not found",false);
        }
        Language language = optionalLanguage.get();
        language.setName(languageDto.getName());

         Set<Category> categories = language.getCategory();
        List<Integer> categoryIds = languageDto.getCategoryIds();
        for (Integer categoryId : categoryIds) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
            if (!optionalCategory.isPresent())
                return new ApiResponse(categoryId+"  CategoryId was not found",false);
            categories.add(optionalCategory.get());
        }
        language.setCategory(categories);
        languageRepository.save(language);
        return new ApiResponse("Category was edited",true);
    }

    public ApiResponse deleteLanguage(Integer id) {
        try {
            languageRepository.deleteById(id);
         return new ApiResponse("Category was deleted",true);
        }catch (Exception e){
        return new ApiResponse("Category was not deleted",false);
    }}
}
