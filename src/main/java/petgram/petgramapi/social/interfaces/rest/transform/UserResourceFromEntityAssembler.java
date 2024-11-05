package petgram.petgramapi.social.interfaces.rest.transform;

import petgram.petgramapi.social.domain.model.aggregates.User;
import petgram.petgramapi.social.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getProfilePicture()
        );
    }
}
