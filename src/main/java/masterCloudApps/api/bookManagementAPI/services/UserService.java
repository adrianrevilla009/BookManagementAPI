package masterCloudApps.api.bookManagementAPI.services;

import masterCloudApps.api.bookManagementAPI.models.Userr;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    Optional<Userr> findById(Long id);
    Page<Userr> findAll(Pageable page);
    Userr save(Userr userr);
    Optional<Userr> deleteById(Long id);
    Userr edit(Long id, Userr userr);
}
