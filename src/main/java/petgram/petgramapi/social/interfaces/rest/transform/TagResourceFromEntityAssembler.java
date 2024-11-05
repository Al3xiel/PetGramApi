package petgram.petgramapi.social.interfaces.rest.transform;

import petgram.petgramapi.social.domain.model.aggregates.Tag;
import petgram.petgramapi.social.interfaces.rest.resources.TagResource;

public class TagResourceFromEntityAssembler {
    public static TagResource toResourceFromEntity(Tag entity) {
        return new TagResource(
                entity.getId(),
                entity.getName(),
                entity.getDescription()
        );
    }
}
