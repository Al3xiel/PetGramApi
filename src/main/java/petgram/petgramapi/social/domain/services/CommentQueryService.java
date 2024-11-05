package petgram.petgramapi.social.domain.services;

import petgram.petgramapi.social.domain.model.aggregates.Comment;
import petgram.petgramapi.social.domain.model.queries.GetAllCommentsByPostIdQuery;
import petgram.petgramapi.social.domain.model.queries.GetCommentByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CommentQueryService {
    List<Comment> handle(GetAllCommentsByPostIdQuery query);
    Optional<Comment> handle(GetCommentByIdQuery query);
}
