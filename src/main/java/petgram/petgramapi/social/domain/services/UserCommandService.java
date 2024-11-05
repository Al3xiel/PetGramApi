package petgram.petgramapi.social.domain.services;

import petgram.petgramapi.social.domain.model.aggregates.User;
import petgram.petgramapi.social.domain.model.commands.CreateUserCommand;
import petgram.petgramapi.social.domain.model.commands.DeleteUserCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(CreateUserCommand command);
    void handle(DeleteUserCommand command);
}
