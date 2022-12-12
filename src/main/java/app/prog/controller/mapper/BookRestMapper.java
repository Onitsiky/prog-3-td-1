package app.prog.controller.mapper;

import app.prog.controller.response.BookResponse;
import app.prog.controller.response.CreateBookResponse;
import app.prog.controller.response.UpdateBookResponse;
import app.prog.model.AuthorEntity;
import app.prog.model.BookEntity;
import app.prog.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
@AllArgsConstructor
public class BookRestMapper {

    private final AuthorRepository repository;
    public BookResponse toRest(BookEntity domain) {
        Optional<AuthorEntity> optional = repository.findById(String.valueOf(domain.getAuthor().getId()));
        return BookResponse.builder()
                .id(domain.getId())
                .title(domain.getTitle())
                .author(optional.get().getName())
                .hasAuthor(domain.hasAuthor())
                .build();
    }

    public BookEntity toDomain(CreateBookResponse rest) {
        Optional<AuthorEntity> optional = repository.findById(String.valueOf(rest.getAuthor()));
        if(optional.isPresent()){
            return BookEntity.builder()
                .author(optional.get())
                .title(rest.getTitle())
                .pageNumber(0) //Constraint not null in database, default value is 0
                .build();
        }
        else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Author." + rest.getAuthor() + " not found");
        }
    }

    public BookEntity toDomain(UpdateBookResponse rest) {
        Optional<AuthorEntity> optional = repository.findById(String.valueOf(rest.getAuthor()));
        if(optional.isPresent()){
            return BookEntity.builder()
                .id(rest.getId())
                .author(optional.get())
                .title(rest.getTitle())
                .pageNumber(0) //Constraint not null in database, default value is 0
                .build();
        }
        else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Author." + rest.getAuthor() + " not found");
        }
    }
}
