package petgram.petgramapi.social.domain.model.aggregates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import petgram.petgramapi.shared.domain.model.entities.AuditableModel;

import java.util.List;

@Getter
@AllArgsConstructor
@Document(collection = "posts")
public class Post extends AuditableModel {
    private String title;
    private String content;
    private String imageUrl;
    private User user;
    private Tag tag;
    private List<Comment> comments;

    public Post(){}
}
