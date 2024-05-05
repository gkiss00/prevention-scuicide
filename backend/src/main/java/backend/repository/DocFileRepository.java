package backend.repository;

import backend.model.entity.DocFile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocFileRepository extends MongoRepository<DocFile, String> {
}
