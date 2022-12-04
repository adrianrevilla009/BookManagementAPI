package masterCloudApps.api.bookManagementAPI.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import masterCloudApps.api.bookManagementAPI.views.View;

@Data
@AllArgsConstructor
@Builder
@Entity
public class Comment {
    @Id
    @JsonView(value = View.Comment.class)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonView(value = View.AuthorComment.class)
    private Book book;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonView(value = View.Comment.class)
    private Userr author;
    @JsonView(value = View.Book.class)
    private String comment;
    @JsonView(value = View.Book.class)
    private int points;

    public Comment() {}

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                "user=" + author +
                ", comment='" + comment + '\'' +
                ", points=" + points +
                '}';
    }
}
