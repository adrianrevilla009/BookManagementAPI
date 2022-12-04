package masterCloudApps.api.bookManagementAPI.data;

import jakarta.annotation.PostConstruct;
import masterCloudApps.api.bookManagementAPI.models.Book;
import masterCloudApps.api.bookManagementAPI.models.Comment;
import masterCloudApps.api.bookManagementAPI.models.Userr;
import masterCloudApps.api.bookManagementAPI.repository.BookRepository;
import masterCloudApps.api.bookManagementAPI.repository.CommentRepository;
import masterCloudApps.api.bookManagementAPI.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class BookData {
    private BookRepository bookRepository;
    private CommentRepository commentRepository;
    private UserRepository userRepository;

    public BookData(BookRepository bookRepository, CommentRepository commentRepository,
                    UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        Userr author = Userr.builder()
                .id(1L)
                .email("user1@gmail.com")
                .nickname("user1")
                .build();
        Book book = Book.builder()
                .id(1L)
                .title("test book 1")
                .author(author)
                .resume("test resume")
                .editorial("test editorial")
                .publicationDate(LocalDate.now())
                .build();
        Comment comment1 = Comment.builder()
                .id(1L)
                .book(book)
                .author(author)
                .comment("test comment 1")
                .points(3)
                .build();
        Comment comment2 = Comment.builder()
                .id(2L)
                .book(book)
                .author(author)
                .comment("test comment 2")
                .points(5)
                .build();
        this.userRepository.save(author);
        this.bookRepository.save(book);
        this.commentRepository.saveAll(Arrays.asList(comment1, comment2));
    }
}
