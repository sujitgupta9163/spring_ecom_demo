package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    @GetMapping("/")
    public String check(){
        return "Ok";
    }

   private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/public/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories , HttpStatus.OK);
    }

    @PostMapping("/api/public/categories")
    public ResponseEntity<String> createCategories(@RequestBody Category category){
        categoryService.createCategories(category);
        return new ResponseEntity<>("Category Create Successfully" , HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long categoryId) {
        try{
            String status = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(status , HttpStatus.OK);
//            return ResponseEntity.ok(status);
//            return ResponseEntity.status(HttpStatus.OK).body(status);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason() , e.getStatusCode());
        }
    }

    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category ,  @PathVariable Long categoryId){
      try {
          Category saveCategory = categoryService.updateCategoty(category , categoryId);
          return new ResponseEntity<>("Category With Category Id "  + categoryId + " Update Successfully ", HttpStatus.OK);
      }catch (ResponseStatusException e){
          return  new ResponseEntity<>(e.getReason() , e.getStatusCode());
      }
    }

}
