package petgram.petgramapi.social.domain.services;

import petgram.petgramapi.social.domain.model.aggregates.Comment;
import petgram.petgramapi.social.domain.model.commands.CreateCommentCommand;
import petgram.petgramapi.social.domain.model.commands.DeleteCommentCommand;

import java.util.Optional;

public interface CommentCommandService {
    void handle(DeleteCommentCommand command);
    Optional<Comment> handle(CreateCommentCommand command);
}
