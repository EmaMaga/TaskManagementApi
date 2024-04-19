package TaskManagementApi.Repository;

import TaskManagementApi.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IUserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity>FindByName(String Name);
    Optional<UserEntity>FindByEmail(String Email);
    Boolean EmailExists(String Email);
}
