package petgram.petgramapi.social.domain.model.aggregates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import petgram.petgramapi.shared.domain.model.entities.AuditableModel;

@Getter
@Document(collection = "comments")
@AllArgsConstructor
public class Comment extends AuditableModel {
    private String content;
    private User user;

    public Comment(){}


}
