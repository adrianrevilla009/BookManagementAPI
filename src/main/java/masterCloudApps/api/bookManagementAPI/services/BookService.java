package masterCloudApps.api.bookManagementAPI.services;

import masterCloudApps.api.bookManagementAPI.dto.BookDto;
import masterCloudApps.api.bookManagementAPI.models.Book;

import java.util.List;

public interface BookService {
    Book getById(Long id);
    List<Book> getAll();
    Book save(Book book);
    Book deleteById(Long id);
    Book edit(Long id, Book book);

    List<BookDto> getAllTitles();
}
