package masterCloudApps.api.bookManagementAPI.models;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import masterCloudApps.api.bookManagementAPI.views.View;

@Data
@AllArgsConstructor
public class User {
    @JsonView(value = View.Book.class)
    private Long id;
    private String nickname;
    private String email;
}
