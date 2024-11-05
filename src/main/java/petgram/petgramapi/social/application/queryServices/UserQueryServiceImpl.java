package petgram.petgramapi.social.application.queryServices;

import org.springframework.stereotype.Service;
import petgram.petgramapi.social.domain.model.aggregates.User;
import petgram.petgramapi.social.domain.model.queries.GetUserByIdQuery;
import petgram.petgramapi.social.domain.services.UserQueryService;
import petgram.petgramapi.social.infrastructure.persistence.mongo.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return Optional.empty();
    }
}
