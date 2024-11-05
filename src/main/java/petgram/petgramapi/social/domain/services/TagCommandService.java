package petgram.petgramapi.social.domain.services;

import petgram.petgramapi.social.domain.model.aggregates.Tag;
import petgram.petgramapi.social.domain.model.commands.CreateTagCommand;
import petgram.petgramapi.social.domain.model.commands.DeleteTagCommand;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface TagCommandService {
    void handle(DeleteTagCommand command);
    Optional<Tag> handle(CreateTagCommand command);
}
