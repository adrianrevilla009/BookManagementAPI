package masterCloudApps.api.bookManagementAPI.services;

import masterCloudApps.api.bookManagementAPI.models.Comment;

import java.util.List;

public interface CommentService {
    Comment getById(Long id);
    List<Comment> getAll();
    Comment save(Comment comment);
    Comment deleteById(Long id);
    Comment edit(Long id, Comment comment);
    List<Comment> getByAuthorId(Long id);
}
