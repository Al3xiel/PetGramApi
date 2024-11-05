package petgram.petgramapi.social.domain.model.aggregates;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;
import petgram.petgramapi.shared.domain.model.entities.AuditableModel;
import petgram.petgramapi.social.domain.model.commands.CreateTagCommand;

@Getter
@Document(collection = "tags")
public class Tag extends AuditableModel {

    @NonNull
    private String name;

    @NonNull
    private String description;

    public Tag(){}

    public Tag(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Tag(CreateTagCommand command){
        this.name = command.name();
        this.description = command.description();
    }
}
