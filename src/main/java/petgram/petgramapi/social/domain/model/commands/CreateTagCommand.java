package petgram.petgramapi.social.domain.model.commands;

public record CreateTagCommand(
        String name,
        String description
) {
}