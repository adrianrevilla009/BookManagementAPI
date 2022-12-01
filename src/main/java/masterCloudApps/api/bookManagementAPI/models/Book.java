package masterCloudApps.api.bookManagementAPI.models;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import masterCloudApps.api.bookManagementAPI.views.View;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class Book {
    @JsonView(value = View.Base.class)
    private Long id;
    private String title;
    private String resume;
    private User author;
    private String editorial;
    private LocalDate publicationDate;

    @JsonView(value = View.Book.class)
    private List<Comment> commentList;

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
