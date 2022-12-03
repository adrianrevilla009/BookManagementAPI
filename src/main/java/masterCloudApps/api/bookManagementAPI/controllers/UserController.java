package masterCloudApps.api.bookManagementAPI.controllers;

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

    @GetMapping(value = "/{id}")
    public ResponseEntity<Userr> findById(@PathVariable("id") Long id) {
        Optional<Userr> userr = this.userService.findById(id);
        return ResponseEntity.of(userr);
    }

    @GetMapping(value = "/")
    public ResponseEntity<Object> findAll(Pageable page) {
        Page<Userr> userrPage = this.userService.findAll(page);
        if (userrPage.getTotalElements() > 0) {
            return ResponseEntity.ok(userrPage);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/")
    public ResponseEntity<Object> save(@RequestBody Userr userr) {
        Userr savedUserr = this.userService.save(userr);
        if (savedUserr != null) {
            URI location = fromCurrentRequest().path("/{id}").buildAndExpand(savedUserr.getId()).toUri();
            return ResponseEntity.created(location).body(savedUserr);
        }
        return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Userr> deleteById(@PathVariable("id") Long id) {
        Optional<Userr> userr = this.userService.deleteById(id);
        return ResponseEntity.of(userr);
    }

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
