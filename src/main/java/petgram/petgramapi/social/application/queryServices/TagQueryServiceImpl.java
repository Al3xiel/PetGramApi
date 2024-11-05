package petgram.petgramapi.social.application.queryServices;

import org.springframework.stereotype.Service;
import petgram.petgramapi.social.domain.model.aggregates.Tag;
import petgram.petgramapi.social.domain.model.queries.GetAllTagsQuery;
import petgram.petgramapi.social.domain.model.queries.GetTagByIdQuery;
import petgram.petgramapi.social.domain.services.TagQueryService;
import petgram.petgramapi.social.infrastructure.persistence.mongo.repositories.TagRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TagQueryServiceImpl implements TagQueryService {

    private final TagRepository tagRepository;

    public TagQueryServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> handle(GetAllTagsQuery query) {
        return List.of();
    }

    @Override
    public Optional<Tag> handle(GetTagByIdQuery query) {
        return Optional.empty();
    }
}
