package app.prog.service;

import app.prog.model.CategoryEntity;
import app.prog.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
  private final CategoryRepository repository;

  public List<CategoryEntity> getCategories(){
    return repository.findAll();
  }

  @Transactional
  public List<CategoryEntity> createCategories(List<CategoryEntity> toCreate){
    return repository.saveAll(toCreate);
  }

  @Transactional
  public List<CategoryEntity> updateCategories (List<CategoryEntity> toUpdate){
    return repository.saveAll(toUpdate);
  }

  public CategoryEntity deleteCategory (int id){
    Optional<CategoryEntity> optional = repository.findById(String.valueOf(id));
    if(optional.isPresent()){
      repository.delete(optional.get());
      return optional.get();
    }
    else {
      throw new ResponseStatusException(HttpStatusCode.valueOf(404), "CategoryEntity." + id + " not found");
    }
  }
}
