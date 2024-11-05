package petgram.petgramapi.social.infrastructure.persistence.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import petgram.petgramapi.social.domain.model.aggregates.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
