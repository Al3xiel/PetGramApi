package petgram.petgramapi.social.domain.services;

import petgram.petgramapi.social.domain.model.aggregates.Tag;
import petgram.petgramapi.social.domain.model.commands.CreateTagCommand;
import petgram.petgramapi.social.domain.model.commands.DeleteTagCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<Tag> handle(CreateTagCommand command);
    void handle(DeleteTagCommand command);
}
