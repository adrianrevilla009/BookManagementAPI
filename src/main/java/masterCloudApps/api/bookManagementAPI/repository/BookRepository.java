package masterCloudApps.api.bookManagementAPI.repository;

import masterCloudApps.api.bookManagementAPI.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT b.* FROM Book b INNER JOIN Userr u ON b.author_id = u.id WHERE u.id = ?1", nativeQuery = true)
    List<Book> findByAuthorId(Long id);
}
