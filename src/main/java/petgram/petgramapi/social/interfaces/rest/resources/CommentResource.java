package petgram.petgramapi.social.interfaces.rest.resources;

public record CommentResource(String id, String content, UserResource user) {
}
