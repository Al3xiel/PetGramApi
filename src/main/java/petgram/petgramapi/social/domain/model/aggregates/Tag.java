package petgram.petgramapi.social.domain.model.aggregates;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;
import petgram.petgramapi.shared.domain.model.entities.AuditableModel;

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
}
