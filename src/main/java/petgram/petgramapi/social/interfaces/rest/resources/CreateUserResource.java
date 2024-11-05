package petgram.petgramapi.social.interfaces.rest.resources;

public record CreateUserResource(
        String username,
        String email,
        String password,
        String profilePicture
) {
}
