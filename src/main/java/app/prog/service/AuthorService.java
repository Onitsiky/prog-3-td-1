package app.prog.service;

import app.prog.model.AuthorEntity;
import app.prog.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService {
  private AuthorRepository repository;

  public List<AuthorEntity> getAuthors (){
    return repository.findAll();
  }

  @Transactional
  public List<AuthorEntity> createAuthors (List<AuthorEntity> toCreate){
    return repository.saveAll(toCreate);
  }

  @Transactional
  public List<AuthorEntity> updateAuthors (List<AuthorEntity> toUpdate){
    return repository.saveAll(toUpdate);
  }

  public AuthorEntity deleteAuthor (int AuthorId){
    Optional<AuthorEntity> optional = repository.findById(String.valueOf(AuthorId));
    if(optional.isPresent()){
      repository.delete(optional.get());
      return optional.get();
    }
    else {
      throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Auhor." + AuthorId + " not found");
    }
  }
}
