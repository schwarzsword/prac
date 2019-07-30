package schwarzsword.repository;

import org.springframework.data.repository.CrudRepository;
import schwarzsword.entity.CourtEntity;

import java.util.List;
import java.util.Optional;

public interface CourtRepository extends CrudRepository<CourtEntity, Integer> {
    Optional<CourtEntity> findByName(String name);
    List<CourtEntity> findAllByNameContains(String part);
    List<CourtEntity> findAll();
}