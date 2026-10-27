package ma.collabnote.collabnote.dto;

import jakarta.validation.constraints.NotNull;
import ma.collabnote.collabnote.entity.Role;

import java.util.UUID;

public record AddUserRequest(
        @NotNull (message = "UserId is required")
        UUID userId,
        @NotNull (message = "Role is required")
        Role role
) {
}
