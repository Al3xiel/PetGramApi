package petgram.petgramapi.social.domain.services;

import petgram.petgramapi.social.domain.model.aggregates.Tag;
import petgram.petgramapi.social.domain.model.queries.GetAllTagsQuery;
import petgram.petgramapi.social.domain.model.queries.GetTagByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TagQueryService {
    List<Tag> handle(GetAllTagsQuery query);
    Optional<Tag> handle(GetTagByIdQuery query);
}
