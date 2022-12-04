package masterCloudApps.api.bookManagementAPI.services;

import masterCloudApps.api.bookManagementAPI.dto.BookDto;
import masterCloudApps.api.bookManagementAPI.models.Book;
import masterCloudApps.api.bookManagementAPI.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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
        return this.bookRepository.save(book);
    }

    @Override
    public Optional<Book> deleteById(Long id) {
        Optional<Book> book = this.bookRepository.findById(id);
        this.bookRepository.deleteById(id);
        return book;
    }

    @Override
    public Book edit(Long id, Book book) {
        this.bookRepository.findById(id).orElseThrow();

        book.setId(id);
        return this.bookRepository.save(book);
    }

    @Override
    public Page<BookDto> findAllTitles(Pageable page) {
        List<BookDto> bookDtoList = this.bookRepository.findAllTitles(page);
        // TODO use rowMapper

    }
}
