package masterCloudApps.api.bookManagementAPI.services;

import masterCloudApps.api.bookManagementAPI.models.Comment;
import masterCloudApps.api.bookManagementAPI.models.User;
import masterCloudApps.api.bookManagementAPI.repository.CommentRepository;
import masterCloudApps.api.bookManagementAPI.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    /*private final UserRepository userRepository;
    private final CommentService commentService;

    public UserServiceImpl(UserRepository userRepository, CommentService commentService) {
        this.userRepository = userRepository;
        this.commentService = commentService;
    }*/

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User deleteById(Long id) {
        /*List<Comment> commentList = this.commentService.getByAuthorId(id);
        if (commentList.size() > 0) {

        }*/
        return null;
    }

    @Override
    public User edit(Long id, User user) {
        return null;
    }
}
