package app.prog.controller;

import app.prog.model.CategoryEntity;
import app.prog.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController {
  private final CategoryService service;

  @GetMapping("/categories")
  public List<CategoryEntity> getCategories (){
    return service.getCategories();
  }

  @PostMapping("/categories")
  public List<CategoryEntity> createCategories (@RequestBody List<CategoryEntity> toCreate){
    return service.createCategories(toCreate);
  }

  @PutMapping("/categories")
  public List<CategoryEntity> updateCategories (@RequestBody List<CategoryEntity> toUpdate){
    return service.updateCategories(toUpdate);
  }

  @DeleteMapping("/categories/{categoryID}")
  public CategoryEntity deleteCategory (@PathVariable int categoryID){
    return service.deleteCategory(categoryID);
  }
}
