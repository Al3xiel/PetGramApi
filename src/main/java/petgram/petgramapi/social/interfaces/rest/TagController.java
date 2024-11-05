package petgram.petgramapi.social.interfaces.rest;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petgram.petgramapi.social.domain.model.aggregates.Tag;
import petgram.petgramapi.social.domain.model.commands.DeleteTagCommand;
import petgram.petgramapi.social.domain.model.queries.GetAllTagsQuery;
import petgram.petgramapi.social.domain.model.queries.GetTagByIdQuery;
import petgram.petgramapi.social.domain.services.TagCommandService;
import petgram.petgramapi.social.domain.services.TagQueryService;
import petgram.petgramapi.social.interfaces.rest.resources.CreateTagResource;
import petgram.petgramapi.social.interfaces.rest.resources.TagResource;
import petgram.petgramapi.social.interfaces.rest.transform.CreateTagCommandFromResourceAssembler;
import petgram.petgramapi.social.interfaces.rest.transform.TagResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value="/api/v1/tags", produces = MediaType.APPLICATION_JSON_VALUE)
@io.swagger.v3.oas.annotations.tags.Tag(name = "Tags", description = "API for managing tags")
public class TagController {

    private final TagQueryService tagQueryService;
    private final TagCommandService tagCommandService;

    public TagController(TagQueryService tagQueryService, TagCommandService tagCommandService) {
        this.tagQueryService = tagQueryService;
        this.tagCommandService = tagCommandService;
    }

    @PostMapping
    @Operation(summary = "Create tag", description = "Create tag")
    public ResponseEntity<TagResource> createTag(@RequestBody CreateTagResource resource) {
        var createTagCommand = CreateTagCommandFromResourceAssembler.toCommandFromResource(resource);
        var tag = this.tagCommandService.handle(createTagCommand);

        if(tag.isEmpty()) return ResponseEntity.badRequest().build();

        var tagResource = TagResourceFromEntityAssembler.toResourceFromEntity(tag.get());
        return ResponseEntity.ok(tagResource);
    }

    @GetMapping
    @Operation(summary = "Get all tags", description = "Get all tags")
    public ResponseEntity<List<TagResource>> getAllTags() {
        var getAllTagsQuery = new GetAllTagsQuery();

        var tags = this.tagQueryService.handle(getAllTagsQuery);

        var tagResources = tags.stream()
                .map(TagResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(tagResources);
    }

    @GetMapping("/{tagId}")
    @Operation(summary = "Get tag by id", description = "Get tag by id")
    public ResponseEntity<TagResource> getTagById(@PathVariable String tagId) {
        var getTagByIdQuery = new GetTagByIdQuery(tagId);
        var tag = this.tagQueryService.handle(getTagByIdQuery);
        if(tag.isEmpty()) return ResponseEntity.notFound().build();
        var tagResource = TagResourceFromEntityAssembler.toResourceFromEntity(tag.get());
        return ResponseEntity.ok(tagResource);
    }

    @DeleteMapping("/{tagId}")
    @Operation(summary = "Delete tag by id", description = "Delete tag by id")
    public ResponseEntity<?> deleteTag(@PathVariable String tagId) {
        var deleteTagCommand = new DeleteTagCommand(tagId);
        this.tagCommandService.handle(deleteTagCommand);
        return ResponseEntity.noContent().build();
    }
}
