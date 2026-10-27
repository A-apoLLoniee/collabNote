package ma.collabnote.collabnote.service;

import ma.collabnote.collabnote.entity.Notebook;
import ma.collabnote.collabnote.exception.NotebookNotFoundException;
import ma.collabnote.collabnote.repository.NotebookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class NotebookService {
    private final NotebookRepository notebookRepository;

    public NotebookService (NotebookRepository notebookRepository){
        this.notebookRepository = notebookRepository;
    }

    public List<Notebook> getNotebook() {
        return notebookRepository.findAll();
    }

    public Notebook addNotebook(Notebook notebook) {
        return notebookRepository.save(notebook);
    }

    public Notebook findNotebookById(UUID id) {
        return notebookRepository.findById(id)
                .orElseThrow(() -> new NotebookNotFoundException(id));
    }

    @Transactional
    public Notebook updateNotebook(UUID ID, Notebook notebook){
        Notebook nb = findNotebookById(ID);
        nb.setTitle(notebook.getTitle());
        return notebookRepository.save(nb);
    }

    @Transactional
    public void deleteNotebook(UUID id){
        Notebook nb = findNotebookById(id);
        notebookRepository.delete(nb);
    }
}
