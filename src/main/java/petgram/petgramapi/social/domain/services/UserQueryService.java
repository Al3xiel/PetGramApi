package petgram.petgramapi.social.domain.services;

import petgram.petgramapi.social.domain.model.aggregates.User;
import petgram.petgramapi.social.domain.model.queries.GetUserByIdQuery;

import java.util.Optional;

public interface UserQueryService {
    Optional<User> handle(GetUserByIdQuery query);
}
