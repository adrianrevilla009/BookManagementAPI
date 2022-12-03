package masterCloudApps.api.bookManagementAPI.services;

import masterCloudApps.api.bookManagementAPI.models.Comment;
import masterCloudApps.api.bookManagementAPI.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return this.commentRepository.findById(id);
    }

    @Override
    public Page<Comment> findAll(Pageable page) {
        return this.commentRepository.findAll(page);
    }

    @Override
    public Comment save(Comment comment) {
        return this.commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> deleteById(Long id) {
        Optional<Comment> comment = this.commentRepository.findById(id);
        this.commentRepository.deleteById(id);
        return comment;
    }

    @Override
    public Comment edit(Long id, Comment comment) {
        this.commentRepository.findById(id).orElseThrow();

        comment.setId(id);
        return this.commentRepository.save(comment);
    }

    @Override
    public Page<Comment> findByAuthorId(Long id, Pageable page) {
        return this.commentRepository.findByAuthorId(id, page);
    }
}
