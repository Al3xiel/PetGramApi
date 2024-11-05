package petgram.petgramapi.social.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petgram.petgramapi.social.domain.model.aggregates.User;
import petgram.petgramapi.social.domain.model.commands.DeleteUserCommand;
import petgram.petgramapi.social.domain.model.queries.GetUserByIdQuery;
import petgram.petgramapi.social.domain.services.UserCommandService;
import petgram.petgramapi.social.domain.services.UserQueryService;
import petgram.petgramapi.social.interfaces.rest.resources.CreateUserResource;
import petgram.petgramapi.social.interfaces.rest.resources.UserResource;
import petgram.petgramapi.social.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import petgram.petgramapi.social.interfaces.rest.transform.UserResourceFromEntityAssembler;

import java.util.List;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value="/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "User", description = "User API")
public class UserController {
    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    public UserController(UserQueryService userQueryService, UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Create a new user")
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource) {
        var createUserCommand = CreateUserCommandFromResourceAssembler.toCommandFromResource(resource);
        var user = this.userCommandService.handle(createUserCommand);

        if(user.isEmpty()) return ResponseEntity.badRequest().build();

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user by id", description = "Get user by id")
    public ResponseEntity<UserResource> getUserById(@PathVariable String userId) {
        var getUserQuery = new GetUserByIdQuery(userId);
        var user = this.userQueryService.handle(getUserQuery);
        if(user.isEmpty()) return ResponseEntity.notFound().build();
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete user by id", description = "Delete user by id")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        var deleteUserCommand = new DeleteUserCommand(userId);
        this.userCommandService.handle(deleteUserCommand);
        return ResponseEntity.noContent().build();
    }
}
