package app.prog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "book")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "author")
    private AuthorEntity author;
    private Integer pageNumber;
    private LocalDate releaseDate;

    @ManyToMany
    @JoinTable(
        name = "book_category",
        joinColumns = @JoinColumn(name = "book"),
        inverseJoinColumns = @JoinColumn(name = "category")
    )
    private List<CategoryEntity> categoryList;
    public boolean hasAuthor() {
        return author != null;
    }

}
