package ma.collabnote.collabnote.controller;

import jakarta.validation.Valid;
import ma.collabnote.collabnote.dto.AddUserRequest;
import ma.collabnote.collabnote.entity.NotebookMembership;
import ma.collabnote.collabnote.entity.Role;
import ma.collabnote.collabnote.service.NotebookMembershipService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class NotebookMembershipController {
    private final NotebookMembershipService notebookMembershipService;
    public NotebookMembershipController(NotebookMembershipService notebookMembershipService) {
        this.notebookMembershipService = notebookMembershipService;
    }

    @GetMapping(value = "/api/notebooks/{notebookId}/users", params = "!email")
    public List<NotebookMembership> findAllUsersByNotebookId (@PathVariable UUID notebookId) {
        return notebookMembershipService.findAllUsersByNotebookId(notebookId);
    }

    @GetMapping(value = "/api/notebooks/{notebookId}/users", params = "email")
    public NotebookMembership findUserByEmail (@PathVariable UUID notebookId, @RequestParam String email) {
        return notebookMembershipService.findUserByEmail(notebookId, email);
    }

    @PostMapping("/api/notebooks/{notebookId}/users")
    @ResponseStatus(HttpStatus.CREATED)
    public NotebookMembership addUser (@PathVariable UUID notebookId, @Valid @RequestBody AddUserRequest addUserRequest) {
        return notebookMembershipService.addUser(notebookId, addUserRequest.userId(), addUserRequest.role());
    }

    @PutMapping("/api/notebooks/{notebookId}/users/{userId}")
    public NotebookMembership editUserRole (@PathVariable UUID notebookId, @PathVariable UUID userId, @Valid @RequestBody Role role) {
        return notebookMembershipService.editUserRole(notebookId, userId, role);
    }

    @DeleteMapping("/api/notebooks/{notebookId}/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUser (@PathVariable UUID notebookId, @PathVariable UUID userId) {
        notebookMembershipService.removeUser(notebookId, userId);
    }

}
