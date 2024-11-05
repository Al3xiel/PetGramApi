package petgram.petgramapi.social.interfaces.rest.transform;

import petgram.petgramapi.social.domain.model.commands.CreateCommentCommand;
import petgram.petgramapi.social.interfaces.rest.resources.CreateCommentResource;

public class CreateCommentCommandFromResourceAssembler {
    public static CreateCommentCommand toCommandFromResource(CreateCommentResource resource) {
        return new CreateCommentCommand(
                resource.content(),
                resource.userId()
        );
    }
}
