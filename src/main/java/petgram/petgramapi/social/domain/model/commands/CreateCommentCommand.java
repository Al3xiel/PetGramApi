package petgram.petgramapi.social.domain.model.commands;

public record CreateCommentCommand(
        String content,
        String userId
) {
}
