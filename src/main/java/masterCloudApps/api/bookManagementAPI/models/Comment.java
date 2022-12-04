package masterCloudApps.api.bookManagementAPI.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    // TODO if I dont ignore, jackson throws recursive exception but if I do, when I save a comment the book is not deserialized so cant be related
    @ManyToOne
    @JsonView(value = View.AuthorComment.class)
    @JsonIgnore
    private Book book;
    @ManyToOne
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
