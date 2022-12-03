package masterCloudApps.api.bookManagementAPI.services;

import masterCloudApps.api.bookManagementAPI.models.Userr;
import masterCloudApps.api.bookManagementAPI.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final CommentService commentService;

    public UserServiceImpl(UserRepository userRepository, CommentService commentService) {
        this.userRepository = userRepository;
        this.commentService = commentService;
    }

    @Override
    public Optional<Userr> findById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public Page<Userr> findAll(Pageable page) {
        return this.userRepository.findAll(page);
    }

    @Override
    public Userr save(Userr userr) {
        return this.userRepository.save(userr);
    }

    @Override
    public Optional<Userr> deleteById(Long id) {
        Optional<Userr> userr = this.userRepository.findById(id);
        this.userRepository.deleteById(id);
        return userr;
    }

    @Override
    public Userr edit(Long id, Userr userr) {
        this.userRepository.findById(id).orElseThrow();

        userr.setId(id);
        return this.userRepository.save(userr);
    }
}
