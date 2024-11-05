package petgram.petgramapi.social.interfaces.rest.transform;

import petgram.petgramapi.social.domain.model.aggregates.Comment;
import petgram.petgramapi.social.interfaces.rest.resources.CommentResource;

public class CommentResourceFromEntityAssembler {
    public static CommentResource toResourceFromEntity(Comment entity) {
        return new CommentResource(
                entity.getId(),
                entity.getContent(),
                UserResourceFromEntityAssembler.toResourceFromEntity(entity.getUser())
        );
    }
}
