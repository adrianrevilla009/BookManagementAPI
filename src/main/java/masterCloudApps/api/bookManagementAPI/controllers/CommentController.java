package masterCloudApps.api.bookManagementAPI.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import masterCloudApps.api.bookManagementAPI.dto.CommentDto;
import masterCloudApps.api.bookManagementAPI.models.Book;
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

    @Operation(summary = "Get a comment by its id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the comment",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation= Comment.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Book not found",
                    content = @Content
            )
    })
    @JsonView(value = View.Comment.class)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Comment> findById(@PathVariable("id") Long id) {
        Optional<Comment> comment = this.commentService.findById(id);
        return ResponseEntity.of(comment);
    }

    @Operation(summary = "Get a all the comments")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Get all comments",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Page.class)
                    )}
            )
    })
    @GetMapping(value = "/")
    public ResponseEntity<Object> findAll(Pageable page) {
        Page<Comment> commentPage = this.commentService.findAll(page);
        if (commentPage.getTotalElements() > 0) {
            return ResponseEntity.ok(commentPage);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Saves a comment")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Comment saved",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Comment.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Book not saved",
                    content = @Content
            )
    })
    @PostMapping(value = "/")
    public ResponseEntity<Object> save(@RequestBody Comment comment) {
        Comment savedComment = this.commentService.save(comment);
        if (savedComment != null) {
            URI location = fromCurrentRequest().path("/{id}").buildAndExpand(savedComment.getId()).toUri();
            return ResponseEntity.created(location).body(savedComment);
        }
        return ResponseEntity.internalServerError().build();
    }

    @Operation(summary = "Delete a comment by its id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Comment deleted",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Comment.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Comment not found",
                    content = @Content
            )
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Comment> deleteById(@PathVariable("id") Long id) {
        Optional<Comment> comment = this.commentService.deleteById(id);
        return ResponseEntity.of(comment);
    }

    @Operation(summary = "Edit a comment")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Comment edited",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Comment.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Comment not found",
                    content = @Content
            )
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> edit(@PathVariable("id") Long id, @RequestBody Comment comment) {
        Comment savedComment = this.commentService.edit(id, comment);
        if (savedComment != null) {
            return ResponseEntity.ok(savedComment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get by author id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Get by author id",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Page.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content
            )
    })
    @GetMapping(value = "/author/{id}")
    public ResponseEntity<Object> findByAuthorId(@PathVariable("id") Long id, Pageable page) {
        Page<CommentDto> commentPage = this.commentService.findByAuthorId(id, page);
        if (commentPage.getTotalElements() > 0) {
            return ResponseEntity.ok(commentPage);
        }
        return ResponseEntity.noContent().build();
    }
}
