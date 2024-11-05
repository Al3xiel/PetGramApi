package petgram.petgramapi.social.infrastructure.persistence.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import petgram.petgramapi.social.domain.model.aggregates.Comment;

import java.util.Optional;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
}
