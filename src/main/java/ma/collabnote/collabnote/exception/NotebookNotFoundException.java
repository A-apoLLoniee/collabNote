package ma.collabnote.collabnote.exception;

import java.util.UUID;

public class NotebookNotFoundException extends RuntimeException{
    public NotebookNotFoundException (UUID id) {
        super("Notebook "+ id + " not found");
    }
}
