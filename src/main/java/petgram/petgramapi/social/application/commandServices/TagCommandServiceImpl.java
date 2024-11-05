package petgram.petgramapi.social.application.commandServices;

import org.springframework.stereotype.Service;
import petgram.petgramapi.social.domain.model.aggregates.Tag;
import petgram.petgramapi.social.domain.model.commands.CreateTagCommand;
import petgram.petgramapi.social.domain.model.commands.DeleteTagCommand;
import petgram.petgramapi.social.domain.services.TagCommandService;
import petgram.petgramapi.social.infrastructure.persistence.mongo.repositories.TagRepository;

import java.util.Optional;

@Service
public class TagCommandServiceImpl implements TagCommandService {

    private final TagRepository tagRepository;

    public TagCommandServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public void handle(DeleteTagCommand command) {
        var tagId = command.tagId();
        if(!this.tagRepository.existsById(tagId)){
            throw new RuntimeException("Tag not found");
        }

        try{
            this.tagRepository.deleteById(tagId);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while deleting tag: " + e.getMessage());
        }
    }

    @Override
    public Optional<Tag> handle(CreateTagCommand command) {
        var tag = new Tag(command);

        try {
            this.tagRepository.save(tag);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while saving tag: " + e.getMessage());
        }

        return Optional.of(tag);
    }
}
