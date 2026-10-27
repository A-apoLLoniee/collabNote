package ma.collabnote.collabnote.repository;

import ma.collabnote.collabnote.entity.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotebookRepository extends JpaRepository<Notebook, UUID> {
}
