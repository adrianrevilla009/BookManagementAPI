package masterCloudApps.api.bookManagementAPI.services;

import masterCloudApps.api.bookManagementAPI.models.Book;
import masterCloudApps.api.bookManagementAPI.models.Comment;
import masterCloudApps.api.bookManagementAPI.models.Userr;
import masterCloudApps.api.bookManagementAPI.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final CommentService commentService;
    private final BookService bookService;

    public UserServiceImpl(UserRepository userRepository, CommentService commentService,
                           BookService bookService) {
        this.userRepository = userRepository;
        this.commentService = commentService;
        this.bookService = bookService;
    }

    @Override
    public Optional<Userr> findById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public Page<Userr> findAll(Pageable page) {
        return this.userRepository.findAll(page);
    }

    @Override
    public Userr save(Userr userr) {
        return this.userRepository.save(userr);
    }

    @Override
    public Optional<Userr> deleteById(Long id) {
        List<Comment> commentList = this.commentService.findByAuthorId(id);
        if (commentList.size() > 0) {
            Optional<Userr> userr = this.userRepository.findById(id);

            commentList.forEach(comment -> this.commentService.deleteById(comment.getId()));

            List<Book> bookList = this.bookService.findByAuthorId(id);
            bookList.forEach(book -> this.bookService.deleteById(book.getId()));
            this.userRepository.deleteById(id);
            return userr;
        }
        return Optional.empty();
    }

    @Override
    public Userr edit(Long id, Userr userr) {
        this.userRepository.findById(id).orElseThrow();

        userr.setId(id);
        return this.userRepository.save(userr);
    }
}
