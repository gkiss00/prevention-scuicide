package backend.repository;

import backend.model.entity.OnCall;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OnCallRepository extends MongoRepository<OnCall, String> {
    List<OnCall> findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(Date StartTime, Date endTime);
}
