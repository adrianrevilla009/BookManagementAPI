package masterCloudApps.api.bookManagementAPI.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import masterCloudApps.api.bookManagementAPI.dto.CommentDto;
import masterCloudApps.api.bookManagementAPI.models.Comment;
import masterCloudApps.api.bookManagementAPI.services.CommentService;
import masterCloudApps.api.bookManagementAPI.views.View;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @JsonView(value = View.Comment.class)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Comment> findById(@PathVariable("id") Long id) {
        Optional<Comment> comment = this.commentService.findById(id);
        return ResponseEntity.of(comment);
    }

    @GetMapping(value = "/")
    public ResponseEntity<Object> findAll(Pageable page) {
        Page<Comment> commentPage = this.commentService.findAll(page);
        if (commentPage.getTotalElements() > 0) {
            return ResponseEntity.ok(commentPage);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/")
    public ResponseEntity<Object> save(@RequestBody Comment comment) {
        Comment savedComment = this.commentService.save(comment);
        if (savedComment != null) {
            URI location = fromCurrentRequest().path("/{id}").buildAndExpand(savedComment.getId()).toUri();
            return ResponseEntity.created(location).body(savedComment);
        }
        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Comment> deleteById(@PathVariable("id") Long id) {
        Optional<Comment> comment = this.commentService.deleteById(id);
        return ResponseEntity.of(comment);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> edit(@PathVariable("id") Long id, @RequestBody Comment comment) {
        Comment savedComment = this.commentService.edit(id, comment);
        if (savedComment != null) {
            return ResponseEntity.ok(savedComment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/author/{id}")
    public ResponseEntity<Object> findByAuthorId(@PathVariable("id") Long id, Pageable page) {
        Page<CommentDto> commentPage = this.commentService.findByAuthorId(id, page);
        if (commentPage.getTotalElements() > 0) {
            return ResponseEntity.ok(commentPage);
        }
        return ResponseEntity.noContent().build();
    }
}
