package masterCloudApps.api.bookManagementAPI.services;

import masterCloudApps.api.bookManagementAPI.models.User;

import java.util.List;

public interface UserService {
    User getById(Long id);
    List<User> getAll();
    User save(User user);
    User deleteById(Long id);
    User edit(Long id, User user);
}
