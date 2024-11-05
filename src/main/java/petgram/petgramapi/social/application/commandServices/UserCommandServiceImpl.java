package petgram.petgramapi.social.application.commandServices;

import org.springframework.stereotype.Service;
import petgram.petgramapi.social.domain.model.aggregates.User;
import petgram.petgramapi.social.domain.model.commands.CreateUserCommand;
import petgram.petgramapi.social.domain.model.commands.DeleteUserCommand;
import petgram.petgramapi.social.domain.services.UserCommandService;
import petgram.petgramapi.social.infrastructure.persistence.mongo.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(CreateUserCommand command) {
        var user = new User(command);

        try{
            this.userRepository.save(user);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while saving user: " + e.getMessage());
        }

        return Optional.of(user);
    }

    @Override
    public void handle(DeleteUserCommand command) {
        var userId = command.userId();
        if (!this.userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User with id " + userId + " does not exist");
        }

        try{
            this.userRepository.deleteById(userId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting user: " + e.getMessage());
        }
    }
}
