package schwarzsword.repository;

import org.springframework.data.repository.CrudRepository;
import schwarzsword.entity.CourtEntity;
import schwarzsword.entity.DocumentEntity;

import java.sql.Timestamp;
import java.util.List;

public interface DocumentRepository extends CrudRepository<DocumentEntity, Integer> {
    List<DocumentEntity> findAllByReceivingDate(Timestamp time);
    List<DocumentEntity> findAllByCourtByCourtName(CourtEntity court);
    List<DocumentEntity> findAllByJudgeNameContains(String part);
    List<DocumentEntity> findAllByPlaintiffContains(String part);
    List<DocumentEntity> findAllByDefendantContains(String part);
    List<DocumentEntity> findAllByExecutionDate(Timestamp time);
    List<DocumentEntity> findAll();
}