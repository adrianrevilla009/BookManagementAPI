package masterCloudApps.api.bookManagementAPI.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String resume;
    private User author;
    private String editorial;
    private LocalDate publicationDate;

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
