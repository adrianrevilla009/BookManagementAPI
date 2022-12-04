package masterCloudApps.api.bookManagementAPI.repository;

import masterCloudApps.api.bookManagementAPI.dto.BookDto;
import masterCloudApps.api.bookManagementAPI.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select b.* FROM Book b")
    Page<Book> findAllTitles(Pageable page);
}
