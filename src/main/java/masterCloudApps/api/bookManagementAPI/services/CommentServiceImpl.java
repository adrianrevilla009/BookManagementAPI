package masterCloudApps.api.bookManagementAPI.services;

import masterCloudApps.api.bookManagementAPI.dto.CommentDto;
import masterCloudApps.api.bookManagementAPI.models.Comment;
import masterCloudApps.api.bookManagementAPI.models.Userr;
import masterCloudApps.api.bookManagementAPI.repository.CommentRepository;
import masterCloudApps.api.bookManagementAPI.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
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
        Userr savedAuthor = this.userRepository.save(comment.getAuthor());
        comment.setAuthor(savedAuthor);
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

        Userr savedAuthor = this.userRepository.save(comment.getAuthor());
        comment.setAuthor(savedAuthor);

        comment.setId(id);
        return this.commentRepository.save(comment);
    }

    @Override
    public Page<CommentDto> findByAuthorId(Long id, Pageable page) {
        Page<Comment> commentPage = this.commentRepository.findByAuthorId(id, page);
        List<CommentDto> commentDtoList = new ArrayList<>();
        commentPage.getContent().forEach(comment -> {
            commentDtoList.add(new CommentDto(
                    comment.getId(),
                    comment.getBook().getId(),
                    comment.getAuthor().getId(),
                    comment.getComment(),
                    comment.getPoints()
            ));
        });
        return new PageImpl<>(commentDtoList, page, commentDtoList.size());
    }

    @Override
    public List<Comment> findByAuthorId(Long id) {
        return this.commentRepository.findListByAuthorId(id);
    }


}
