package masterCloudApps.api.bookManagementAPI.controllers;

import masterCloudApps.api.bookManagementAPI.models.User;
import masterCloudApps.api.bookManagementAPI.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(name = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(name = "/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
        User user = this.userService.getById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(name = "/")
    public ResponseEntity<Object> getAll() {
        // TODO pagination
        List<User> userList = this.userService.getAll();
        if (userList.size() > 0) {
            return ResponseEntity.ok(userList);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping(name = "/")
    public ResponseEntity<Object> save(@RequestBody User user) {
        User savedUser = this.userService.save(user);
        if (savedUser != null) {
            URI location = fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
            return ResponseEntity.created(location).body(savedUser);
        }
        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping(name = "/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
        User deletedUser = this.userService.deleteById(id);
        if (deletedUser != null) {
            return ResponseEntity.ok(deletedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(name = "/{id}")
    public ResponseEntity<Object> edit(@PathVariable("id") Long id, @RequestBody User user) {
        User userComment = this.userService.edit(id, user);
        if (userComment != null) {
            return ResponseEntity.ok(userComment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
