package masterCloudApps.api.bookManagementAPI.services;

import masterCloudApps.api.bookManagementAPI.dto.BookDto;
import masterCloudApps.api.bookManagementAPI.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookService {
    Optional<Book> findById(Long id);
    Page<Book> findAll(Pageable page);
    Book save(Book book);
    Optional<Book> deleteById(Long id);
    Book edit(Long id, Book book);

    Page<BookDto> findAllTitles(Pageable page);
}
