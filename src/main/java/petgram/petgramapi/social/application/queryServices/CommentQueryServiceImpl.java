package petgram.petgramapi.social.application.queryServices;

import org.springframework.stereotype.Service;
import petgram.petgramapi.social.domain.model.aggregates.Comment;
import petgram.petgramapi.social.domain.model.queries.GetAllCommentsByPostIdQuery;
import petgram.petgramapi.social.domain.model.queries.GetCommentByIdQuery;
import petgram.petgramapi.social.domain.services.CommentQueryService;
import petgram.petgramapi.social.infrastructure.persistence.mongo.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentQueryServiceImpl implements CommentQueryService {

    private final CommentRepository commentRepository;

    public CommentQueryServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> handle(GetAllCommentsByPostIdQuery query) {
        return this.commentRepository.findAll();
    }

    @Override
    public Optional<Comment> handle(GetCommentByIdQuery query) {
        return this.commentRepository.findById(query.id());
    }
}
