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
public class Userr {
    @Id
    @JsonView(value = View.Book.class)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nickname;
    private String email;

    public Userr() {}
}
