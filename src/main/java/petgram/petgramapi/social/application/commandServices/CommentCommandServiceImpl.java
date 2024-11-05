package petgram.petgramapi.social.application.commandServices;

import org.springframework.stereotype.Service;
import petgram.petgramapi.social.domain.model.aggregates.Comment;
import petgram.petgramapi.social.domain.model.commands.CreateCommentCommand;
import petgram.petgramapi.social.domain.model.commands.DeleteCommentCommand;
import petgram.petgramapi.social.domain.services.CommentCommandService;
import petgram.petgramapi.social.infrastructure.persistence.mongo.repositories.CommentRepository;
import petgram.petgramapi.social.infrastructure.persistence.mongo.repositories.UserRepository;

import java.util.Optional;

@Service
public class CommentCommandServiceImpl implements CommentCommandService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentCommandServiceImpl(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void handle(DeleteCommentCommand command) {
        var commentId = command.commentId();
        if(!this.commentRepository.existsById(commentId)){
            throw new RuntimeException("Comment not found");
        }

        try{
            this.commentRepository.deleteById(commentId);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while deleting comment: " + e.getMessage());
        }
    }

    @Override
    public Optional<Comment> handle(CreateCommentCommand command) {
        var comment = new Comment(command);
        var user = this.userRepository.findById(command.userId()).orElseThrow(() -> new IllegalArgumentException("User with id " + command.userId() + " not found"));
        comment.setUser(user);

        try {
            this.commentRepository.save(comment);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while saving comment: " + e.getMessage());
        }

        return Optional.of(comment);
    }
}
