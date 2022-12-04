package masterCloudApps.api.bookManagementAPI.repository;

import masterCloudApps.api.bookManagementAPI.dto.CommentDto;
import masterCloudApps.api.bookManagementAPI.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT c.* FROM Comment c INNER JOIN Userr u ON c.author_id = u.id WHERE u.id = ?1", nativeQuery = true)
    Page<Comment> findByAuthorId(Long id, Pageable page);

    @Query(value = "SELECT c.* FROM Comment c INNER JOIN Userr u ON c.author_id = u.id WHERE u.id = ?1", nativeQuery = true)
    List<Comment> findListByAuthorId(Long id);
}
