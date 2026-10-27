package ma.collabnote.collabnote.repository;

import ma.collabnote.collabnote.entity.NotebookMembership;
import ma.collabnote.collabnote.entity.NotebookMembershipID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface NotebookMembershipRepository extends JpaRepository<NotebookMembership, NotebookMembershipID> {
    List<NotebookMembership> findById_IdNB(UUID notebookId);

    Optional<NotebookMembership> findById_IdNBAndUser_email(UUID notebookId, String email);
}
