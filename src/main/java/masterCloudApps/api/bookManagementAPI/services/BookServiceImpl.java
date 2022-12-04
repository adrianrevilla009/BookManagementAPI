package masterCloudApps.api.bookManagementAPI.services;

import masterCloudApps.api.bookManagementAPI.dto.BookDto;
import masterCloudApps.api.bookManagementAPI.mappers.BookMapper;
import masterCloudApps.api.bookManagementAPI.models.Book;
import masterCloudApps.api.bookManagementAPI.models.Comment;
import masterCloudApps.api.bookManagementAPI.models.Userr;
import masterCloudApps.api.bookManagementAPI.repository.BookRepository;
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
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper,
                           CommentRepository commentRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Page<Book> findAll(Pageable page) {
        return this.bookRepository.findAll(page);
    }

    @Override
    public Book save(Book book) {
        Userr savedAuthor = this.userRepository.save(book.getAuthor());
        book.setAuthor(savedAuthor);

        Book savedBook = this.bookRepository.save(book);
        // TODO not able to define cascade, so entity are persisted separately
        if (book.getCommentList().size() > 0) {
            book.getCommentList().forEach(comment -> comment.setBook(savedBook));
            for (Comment comment : book.getCommentList()) {
                Userr savedCommentAuthor = this.userRepository.save(comment.getAuthor());
                comment.setAuthor(savedCommentAuthor);
            }
            this.commentRepository.saveAll(book.getCommentList());
        }
        return savedBook;
    }

    @Override
    public Optional<Book> deleteById(Long id) {
        Optional<Book> book = this.bookRepository.findById(id);
        // TODO not able to define cascade, so entity are deleted separately
        if (book.isPresent() && book.get().getCommentList().size() > 0) {
            this.commentRepository.deleteAll(book.get().getCommentList());
        }
        this.bookRepository.deleteById(id);
        return book;
    }

    @Override
    public Book edit(Long id, Book book) {
        this.bookRepository.findById(id).orElseThrow();

        Userr savedAuthor = this.userRepository.save(book.getAuthor());
        book.setAuthor(savedAuthor);

        book.setId(id);
        Book savedBook = this.bookRepository.save(book);
        // TODO not able to define cascade, so entity are persisted separately
        if (book.getCommentList().size() > 0) {
            book.getCommentList().forEach(comment -> comment.setBook(savedBook));
            for (Comment comment : book.getCommentList()) {
                Userr savedCommentAuthor = this.userRepository.save(comment.getAuthor());
                comment.setAuthor(savedCommentAuthor);
            }
            this.commentRepository.saveAll(book.getCommentList());
        }
        return savedBook;
    }

    @Override
    public Page<BookDto> findAllTitles(Pageable page) {
        Page<Book> bookPage = this.bookRepository.findAll(page);
        List<BookDto> bookDtoList = bookPage.getContent().stream().map(this.bookMapper::toDto).toList();
        return new PageImpl<>(bookDtoList, page, bookDtoList.size());
    }

    @Override
    public List<Book> findByAuthorId(Long id) {
        return this.bookRepository.findByAuthorId(id);
    }
}
