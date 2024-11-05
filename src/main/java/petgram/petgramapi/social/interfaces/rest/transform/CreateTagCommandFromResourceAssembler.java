package petgram.petgramapi.social.interfaces.rest.transform;

import petgram.petgramapi.social.domain.model.commands.CreateTagCommand;
import petgram.petgramapi.social.interfaces.rest.resources.CreateTagResource;

public class CreateTagCommandFromResourceAssembler {
    public static CreateTagCommand toCommandFromResource(CreateTagResource resource) {
        return new CreateTagCommand(
                resource.name(),
                resource.description()
        );
    }
}
