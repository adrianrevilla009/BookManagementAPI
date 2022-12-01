package masterCloudApps.api.bookManagementAPI.controllers;

import masterCloudApps.api.bookManagementAPI.models.Book;
import masterCloudApps.api.bookManagementAPI.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(name = "/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(name = "/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
        Book book = this.bookService.getById(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(name = "/")
    public ResponseEntity<Object> getAll() {
        List<Book> bookList = this.bookService.getAll();
        if (bookList.size() > 0) {
            return ResponseEntity.ok(bookList);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping(name = "/")
    public ResponseEntity<Object> save(@RequestBody Book book) {
        Book savedBook = this.bookService.save(book);
        if (savedBook != null) {
            URI location = fromCurrentRequest().path("/{id}").buildAndExpand(savedBook.getId()).toUri();
            return ResponseEntity.created(location).body(savedBook);
        }
        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping(name = "/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
        Book deletedBook = this.bookService.deleteById(id);
        if (deletedBook != null) {
            return ResponseEntity.ok(deletedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(name = "/{id}")
    public ResponseEntity<Object> edit(@PathVariable("id") Long id, @RequestBody Book book) {
        Book savedBook = this.bookService.edit(id, book);
        if (savedBook != null) {
            return ResponseEntity.ok(savedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
