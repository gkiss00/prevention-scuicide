package backend.repository;

import backend.model.entity.Shift;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRepository extends MongoRepository<Shift, String> {
    List<Shift> findByDay(String day);
    @Query("{'time' : { $gte: ?0, $lte: ?1 } }")
    List<Shift> findByTimeBetween(int startDate, int endDate);
}
