package masterCloudApps.api.bookManagementAPI.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import masterCloudApps.api.bookManagementAPI.models.Comment;
import masterCloudApps.api.bookManagementAPI.services.CommentService;
import masterCloudApps.api.bookManagementAPI.views.View;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
        Comment comment = this.commentService.getById(id);
        if (comment != null) {
            return ResponseEntity.ok(comment);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/")
    public ResponseEntity<Object> getAll() {
        // TODO pagination
        List<Comment> commentList = this.commentService.getAll();
        if (commentList.size() > 0) {
            return ResponseEntity.ok(commentList);
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
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
        Comment deletedComment = this.commentService.deleteById(id);
        if (deletedComment != null) {
            return ResponseEntity.ok(deletedComment);
        } else {
            return ResponseEntity.notFound().build();
        }
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

    @JsonView(value = View.Base.class)
    @GetMapping(value = "/author/{id}")
    public ResponseEntity<Object> getByAuthorId(@PathVariable("id") Long id) {
        List<Comment> commentList = this.commentService.getByAuthorId(id);
        if (commentList.size() > 0) {
            return ResponseEntity.ok(commentList);
        }
        return ResponseEntity.noContent().build();
    }
}
