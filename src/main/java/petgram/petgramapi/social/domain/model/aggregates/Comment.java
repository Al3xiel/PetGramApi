package petgram.petgramapi.social.domain.model.aggregates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import petgram.petgramapi.shared.domain.model.entities.AuditableModel;
import petgram.petgramapi.social.domain.model.commands.CreateCommentCommand;

@Getter
@Document(collection = "comments")
@AllArgsConstructor
public class Comment extends AuditableModel {
    private String content;
    @Setter
    private User user;

    public Comment(){}

    public Comment(CreateCommentCommand command) {
        this.content = command.content();
    }

}
