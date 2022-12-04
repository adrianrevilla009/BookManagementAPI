package masterCloudApps.api.bookManagementAPI.services;

import masterCloudApps.api.bookManagementAPI.dto.CommentDto;
import masterCloudApps.api.bookManagementAPI.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> findById(Long id);
    Page<Comment> findAll(Pageable page);
    Comment save(Comment comment);
    Optional<Comment> deleteById(Long id);
    Comment edit(Long id, Comment comment);
    Page<CommentDto> findByAuthorId(Long id, Pageable page);

    List<Comment> findByAuthorId(Long id);
}
