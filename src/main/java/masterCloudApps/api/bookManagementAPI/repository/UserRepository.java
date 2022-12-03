package masterCloudApps.api.bookManagementAPI.repository;

import masterCloudApps.api.bookManagementAPI.models.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Userr, Long> {
}
