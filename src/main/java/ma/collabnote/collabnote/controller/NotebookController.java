package ma.collabnote.collabnote.controller;

import jakarta.validation.Valid;
import ma.collabnote.collabnote.entity.Notebook;
import ma.collabnote.collabnote.service.NotebookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class NotebookController {
    private final NotebookService notebookService;
    public NotebookController(NotebookService notebookService) {
        this.notebookService = notebookService;
    }

    @GetMapping("/api/notebooks")
    public List<Notebook> getNotebook() {
        return notebookService.getNotebook();
    }

    @GetMapping("/api/notebooks/{notebookId}")
    public Notebook findNotebookById(@PathVariable UUID notebookId) {
        return notebookService.findNotebookById(notebookId);
    }

    @PostMapping("/api/notebooks")
    @ResponseStatus(HttpStatus.CREATED)
    public Notebook addNotebook(@Valid @RequestBody Notebook notebook) {
        return notebookService.addNotebook(notebook);
    }

    @PutMapping("/api/notebooks/{notebookId}")
    public Notebook updateNotebook(@PathVariable UUID notebookId, @Valid @RequestBody Notebook notebook) {
        return notebookService.updateNotebook(notebookId, notebook);
    }

    @DeleteMapping("/api/notebooks/{notebookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNotebook(@PathVariable UUID notebookId) {
        notebookService.deleteNotebook(notebookId);
    }

}
