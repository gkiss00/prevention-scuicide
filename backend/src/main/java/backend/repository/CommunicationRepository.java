package backend.repository;

import backend.model.entity.Communication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunicationRepository extends MongoRepository<Communication, String> {
}
