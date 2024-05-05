package backend.repository;

import backend.model.entity.Supervision;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupervisionRepository extends MongoRepository<Supervision, String> {
}
