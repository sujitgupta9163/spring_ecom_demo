package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService{
    private List<Category> categories = new ArrayList<>();
    private  Long nextId = 1L;
    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategories(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND , "Resource not found"));
                if (category == null){
                    return "Category not found";
                }
                categories.remove(category);
        return " Category with CategoryId : " + categoryId + " deleted successfully";
    }

    @Override
    public Category updateCategoty(Category category, Long categoryId) {
       Optional<Category> categoryOptional = categories.stream()
                .filter(c ->c.getCategoryId().equals(categoryId))
                .findFirst();
       if (categoryOptional.isPresent()){
           Category exitsCategory = categoryOptional.get();
           exitsCategory.setCategoryName(category.getCategoryName());
           return exitsCategory;
       }else {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Category Not Found");
       }
    }


}
