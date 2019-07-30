package schwarzsword.repository;

import org.springframework.data.repository.CrudRepository;
import schwarzsword.entity.UsersEntity;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<UsersEntity, Integer> {
    Optional<UsersEntity> findByUsername(String name);
    boolean existsByUsername(String username);
}