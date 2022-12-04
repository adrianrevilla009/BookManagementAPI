package masterCloudApps.api.bookManagementAPI.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import masterCloudApps.api.bookManagementAPI.data.BookData;
import masterCloudApps.api.bookManagementAPI.dto.BookDto;
import masterCloudApps.api.bookManagementAPI.models.Book;
import masterCloudApps.api.bookManagementAPI.services.BookService;
import masterCloudApps.api.bookManagementAPI.views.View;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(value = "/books")
public class BookController {
    private final BookService bookService;
    private final BookData bookData;

    public BookController(BookService bookService, BookData bookData) {
        this.bookService = bookService;
        this.bookData = bookData;
    }

    @JsonView(value = View.Book.class)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> findById(@PathVariable("id") Long id) {
        Optional<Book> book = this.bookService.findById(id);
        return ResponseEntity.of(book);
    }

    @GetMapping(value = "/")
    public ResponseEntity<Object> findAll(Pageable page) {
        Page<Book> bookPage = this.bookService.findAll(page);
        if (bookPage.getTotalElements() > 0) {
            return ResponseEntity.ok(bookPage);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/")
    public ResponseEntity<Object> save(@RequestBody Book book) {
        Book savedBook = this.bookService.save(book);
        if (savedBook != null) {
            URI location = fromCurrentRequest().path("/{id}").buildAndExpand(savedBook.getId()).toUri();
            return ResponseEntity.created(location).body(savedBook);
        }
        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Book> deleteById(@PathVariable("id") Long id) {
        Optional<Book> book = this.bookService.deleteById(id);
        return ResponseEntity.of(book);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> edit(@PathVariable("id") Long id, @RequestBody Book book) {
        Book savedBook = this.bookService.edit(id, book);
        if (savedBook != null) {
            return ResponseEntity.ok(savedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/titles")
    public ResponseEntity<Object> findAllTitles(Pageable page) {
        Page<BookDto> bookDtoPage = this.bookService.findAllTitles(page);
        if (bookDtoPage.getTotalElements() > 0) {
            return ResponseEntity.ok(bookDtoPage);
        }
        return ResponseEntity.noContent().build();
    }
}
