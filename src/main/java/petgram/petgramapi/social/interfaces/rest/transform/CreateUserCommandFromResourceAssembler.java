package petgram.petgramapi.social.interfaces.rest.transform;

import petgram.petgramapi.social.domain.model.commands.CreateUserCommand;
import petgram.petgramapi.social.interfaces.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {
    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(
                resource.username(),
                resource.email(),
                resource.password(),
                resource.profilePicture()
        );
    }
}
