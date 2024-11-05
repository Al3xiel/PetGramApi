package petgram.petgramapi.social.domain.model.commands;

public record CreatePostCommand(
        String title,
        String description,
        String imageUrl
) {
}
