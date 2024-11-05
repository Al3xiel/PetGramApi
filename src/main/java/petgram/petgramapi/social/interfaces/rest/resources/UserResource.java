package petgram.petgramapi.social.interfaces.rest.resources;

public record UserResource(
        String id,
        String username,
        String email,
        String password,
        String profilePicture
) {
}
