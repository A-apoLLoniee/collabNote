package ma.collabnote.collabnote.exception;

import ma.collabnote.collabnote.entity.NotebookMembershipID;

import java.util.UUID;

public class MembershipNotFound extends RuntimeException{
    public MembershipNotFound(NotebookMembershipID id) {
        super("User not found");
    }

    public MembershipNotFound(UUID notebookId, String email) {
        super("User '" + email + "' not found");
    }
}
