package masterCloudApps.api.bookManagementAPI.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import masterCloudApps.api.bookManagementAPI.models.Comment;
import masterCloudApps.api.bookManagementAPI.models.Userr;
import masterCloudApps.api.bookManagementAPI.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get user by its id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation= Userr.class)
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
    @GetMapping(value = "/{id}")
    public ResponseEntity<Userr> findById(@PathVariable("id") Long id) {
        Optional<Userr> userr = this.userService.findById(id);
        return ResponseEntity.of(userr);
    }

    @Operation(summary = "Get a all users")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Get all users",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Page.class)
                    )}
            )
    })
    @GetMapping(value = "/")
    public ResponseEntity<Object> findAll(Pageable page) {
        Page<Userr> userrPage = this.userService.findAll(page);
        if (userrPage.getTotalElements() > 0) {
            return ResponseEntity.ok(userrPage);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Saves a user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User saved",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Userr.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not saved",
                    content = @Content
            )
    })
    @PostMapping(value = "/")
    public ResponseEntity<Object> save(@RequestBody Userr userr) {
        Userr savedUserr = this.userService.save(userr);
        if (savedUserr != null) {
            URI location = fromCurrentRequest().path("/{id}").buildAndExpand(savedUserr.getId()).toUri();
            return ResponseEntity.created(location).body(savedUserr);
        }
        return ResponseEntity.internalServerError().build();
    }

    @Operation(summary = "Delete a user by its id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User deleted",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Userr.class)
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
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Userr> deleteById(@PathVariable("id") Long id) {
        Optional<Userr> userr = this.userService.deleteById(id);
        return ResponseEntity.of(userr);
    }

    @Operation(summary = "Edit a user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User edited",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Userr.class)
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
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> edit(@PathVariable("id") Long id, @RequestBody Userr userr) {
        Userr userrComment = this.userService.edit(id, userr);
        if (userrComment != null) {
            return ResponseEntity.ok(userrComment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
