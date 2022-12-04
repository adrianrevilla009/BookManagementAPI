package masterCloudApps.api.bookManagementAPI.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import masterCloudApps.api.bookManagementAPI.views.View;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(value = View.Book.class)
    private Long id;

    private String title;
    private String resume;
    @ManyToOne
    private Userr author;
    private String editorial;
    private LocalDate publicationDate;

    // @OneToMany(mappedBy="book", cascade=CascadeType.ALL, orphanRemoval=true)
    // TODO not able to make cascade relation, entity already merged exception
    @OneToMany(mappedBy="book")
    @JsonView(value = View.Book.class)
    private List<Comment> commentList;

    public Book() {}

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", resume='" + resume + '\'' +
                ", author=" + author +
                ", editorial='" + editorial + '\'' +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
