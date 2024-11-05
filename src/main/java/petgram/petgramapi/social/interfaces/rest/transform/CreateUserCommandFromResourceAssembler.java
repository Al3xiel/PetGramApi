package petgram.petgramapi.social.interfaces.rest.transform;

import petgram.petgramapi.social.domain.model.commands.CreateUserCommand;

public class CreateUserCommandFromResourceAssembler {
    public static CreateUserCommand toCommandFromResource(CreateUserCommand resource) {
        return new CreateUserCommand(
                resource.username(),
                resource.email(),
                resource.password(),
                resource.profilePicture()
        );
    }
}
