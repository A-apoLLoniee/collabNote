package ma.collabnote.collabnote.exception;

import java.util.UUID;

public class UserNotFound extends RuntimeException {
    public UserNotFound(UUID id) {
        super("User "+ id + " not found");
    }
}
