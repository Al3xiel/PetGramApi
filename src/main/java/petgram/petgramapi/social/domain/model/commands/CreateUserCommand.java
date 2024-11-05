package petgram.petgramapi.social.domain.model.commands;

public record CreateUserCommand(
        String username,
        String email,
        String password,
        String profilePicture
) {
}
