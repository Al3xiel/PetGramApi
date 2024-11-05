package petgram.petgramapi.social.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petgram.petgramapi.social.domain.model.aggregates.Comment;
import petgram.petgramapi.social.domain.model.commands.DeleteCommentCommand;
import petgram.petgramapi.social.domain.model.queries.GetAllCommentsByPostIdQuery;
import petgram.petgramapi.social.domain.model.queries.GetCommentByIdQuery;
import petgram.petgramapi.social.domain.services.CommentCommandService;
import petgram.petgramapi.social.domain.services.CommentQueryService;
import petgram.petgramapi.social.interfaces.rest.resources.CommentResource;
import petgram.petgramapi.social.interfaces.rest.resources.CreateCommentResource;
import petgram.petgramapi.social.interfaces.rest.transform.CommentResourceFromEntityAssembler;
import petgram.petgramapi.social.interfaces.rest.transform.CreateCommentCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value="/api/v1/comments", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Comments", description = "API for managing comments")
public class CommentController {
    private final CommentQueryService commentQueryService;
    private final CommentCommandService commentCommandService;

    public CommentController(CommentQueryService commentQueryService, CommentCommandService commentCommandService) {
        this.commentQueryService = commentQueryService;
        this.commentCommandService = commentCommandService;
    }

    @PostMapping
    @Operation(summary = "Create comment", description = "Create comment")
    public ResponseEntity<CommentResource> createComment(@RequestBody CreateCommentResource resource) {
        var createCommentCommand = CreateCommentCommandFromResourceAssembler.toCommandFromResource(resource);
        var comment = this.commentCommandService.handle(createCommentCommand);

        if(comment.isEmpty()) return ResponseEntity.badRequest().build();

        var commentResource = CommentResourceFromEntityAssembler.toResourceFromEntity(comment.get());
        return ResponseEntity.ok(commentResource);
    }

    @GetMapping
    @Operation(summary = "Get all comments", description = "Get all comments")
    public ResponseEntity<List<CommentResource>> getAllComments() {
        var getAllCommentsQuery = new GetAllCommentsByPostIdQuery();

        var comments = this.commentQueryService.handle(getAllCommentsQuery);

        var commentResources = comments.stream()
                .map(CommentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(commentResources);
    }

    @GetMapping("/{commentId}")
    @Operation(summary = "Get comment by id", description = "Get comment by id")
    public ResponseEntity<CommentResource> getCommentById(@PathVariable String commentId) {
        var getCommentByIdQuery = new GetCommentByIdQuery(commentId);
        var comment = this.commentQueryService.handle(getCommentByIdQuery);
        if(comment.isEmpty()) return ResponseEntity.notFound().build();
        var commentResource = CommentResourceFromEntityAssembler.toResourceFromEntity(comment.get());
        return ResponseEntity.ok(commentResource);
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "Delete comment", description = "Delete comment")
    public ResponseEntity<?> deleteComment(@PathVariable String commentId) {
        var deleteCommentCommand = new DeleteCommentCommand(commentId);
        this.commentCommandService.handle(deleteCommentCommand);
        return ResponseEntity.ok().build();
    }
}
