package app.prog.controller.mapper;

import app.prog.controller.response.BookResponse;
import app.prog.controller.response.CreateBookResponse;
import app.prog.controller.response.UpdateBookResponse;
import app.prog.model.AuthorEntity;
import app.prog.model.BookEntity;
import app.prog.model.CategoryEntity;
import app.prog.repository.AuthorRepository;
import app.prog.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class BookRestMapper {

    private final AuthorRepository repository;
    private final CategoryRepository categoryRepository;
    public BookResponse toRest(BookEntity domain) {
        Optional<AuthorEntity> author = repository.findById(String.valueOf(domain.getAuthor().getId()));
        return BookResponse.builder()
                .id(domain.getId())
                .title(domain.getTitle())
                .author(author.get().getName())
                .categories(domain.getCategoryList())
                .hasAuthor(domain.hasAuthor())
                .build();
    }

    public BookEntity toDomain(CreateBookResponse rest) {
        AuthorEntity author = repository.getByName(rest.getAuthor());
        if(author != null){
            return BookEntity.builder()
                .author(author)
                .title(rest.getTitle())
                .pageNumber(0) //Constraint not null in database, default value is 0
                .build();
        }
        else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Author." + rest.getAuthor() + " not found");
        }
    }

    public BookEntity toDomain(UpdateBookResponse rest) {
        AuthorEntity author = repository.getByName(rest.getAuthor());
        if(author != null){
            return BookEntity.builder()
                .id(rest.getId())
                .author(author)
                .title(rest.getTitle())
                .pageNumber(0) //Constraint not null in database, default value is 0
                .build();
        }
        else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Author." + rest.getAuthor() + " not found");
        }
    }
}
