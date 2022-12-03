package masterCloudApps.api.bookManagementAPI.services;

import masterCloudApps.api.bookManagementAPI.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CommentService {
    Optional<Comment> findById(Long id);
    Page<Comment> findAll(Pageable page);
    Comment save(Comment comment);
    Optional<Comment> deleteById(Long id);
    Comment edit(Long id, Comment comment);
    Page<Comment> findByAuthorId(Long id, Pageable page);
}
