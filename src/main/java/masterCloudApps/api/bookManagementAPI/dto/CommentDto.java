package masterCloudApps.api.bookManagementAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private Long bookId;
    private Long authorId;
    private String comment;
    private int points;
}
