package petgram.petgramapi.social.domain.model.aggregates;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import petgram.petgramapi.shared.domain.model.entities.AuditableModel;
import petgram.petgramapi.social.domain.model.commands.CreateUserCommand;

@Getter
@Document(collection = "users")
public class User extends AuditableModel {
    private String username;
    private String email;
    private String password;
    private String profilePicture;

    public User(){}

    public User(String username, String email, String password, String profilePicture){
        this.username = username;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
    }

    public User (CreateUserCommand command){
        this.username = command.username();
        this.email = command.email();
        this.password = command.password();
        this.profilePicture = command.profilePicture();
    }
}
