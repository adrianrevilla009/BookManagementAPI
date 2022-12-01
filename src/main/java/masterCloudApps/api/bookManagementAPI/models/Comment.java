package masterCloudApps.api.bookManagementAPI.models;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import masterCloudApps.api.bookManagementAPI.views.View;

@Data
@AllArgsConstructor
public class Comment {
    @JsonView(value = View.Book.class)
    private Long id;

    @JsonView(value = View.Base.class)
    private Book book;
    @JsonView(value = View.Book.class)
    private User author;
    private String comment;
    private int points;

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
