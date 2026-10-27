package ma.collabnote.collabnote.service;

import ma.collabnote.collabnote.entity.NotebookMembership;
import ma.collabnote.collabnote.entity.NotebookMembershipID;
import ma.collabnote.collabnote.entity.Role;
import ma.collabnote.collabnote.entity.Notebook;
import ma.collabnote.collabnote.entity.User;
import ma.collabnote.collabnote.exception.MembershipNotFound;
import ma.collabnote.collabnote.repository.NotebookMembershipRepository;
import ma.collabnote.collabnote.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class NotebookMembershipService {
    private final NotebookMembershipRepository notebookMembershipRepository;
    private final NotebookService notebookService;
    private final UserRepository userRepository;

    public NotebookMembershipService (NotebookMembershipRepository notebookMembershipRepository,
                                      NotebookService notebookService,
                                      UserRepository userRepository) {
        this.notebookMembershipRepository= notebookMembershipRepository;
        this.notebookService = notebookService;
        this.userRepository = userRepository;
    }

    public List<NotebookMembership> findAllUsersByNotebookId (UUID notebookId) {
        return notebookMembershipRepository.findById_IdNB(notebookId);
    }

    public NotebookMembership findById (NotebookMembershipID id) {
        return notebookMembershipRepository.findById(id)
                .orElseThrow(() -> new MembershipNotFound(id));
    }

    public NotebookMembership findUserByEmail (UUID notebookId, String email) {
        return notebookMembershipRepository.findById_IdNBAndUser_email(notebookId, email)
                .orElseThrow(() -> new MembershipNotFound(notebookId, email));
    }

    @Transactional
    public void removeUser (UUID notebookId, UUID userId){
        NotebookMembershipID id = new NotebookMembershipID(notebookId, userId);
        NotebookMembership member = findById(id);
        notebookMembershipRepository.delete(member);
    }

    @Transactional
    public NotebookMembership editUserRole (UUID notebookId, UUID userId, Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Role is required");
        }
        NotebookMembershipID id = new NotebookMembershipID(notebookId, userId);
        NotebookMembership member = findById(id);
        member.setRole(role);
        return notebookMembershipRepository.save(member);
    }

    @Transactional
    public NotebookMembership addUser(UUID notebookId, UUID userId, Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Role is required");
        }

        Notebook notebook = notebookService.findNotebookById(notebookId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User " + userId + " not found"));

        NotebookMembershipID id = new NotebookMembershipID(notebookId, userId);
        if (notebookMembershipRepository.existsById(id)) {
            throw new IllegalArgumentException("User is already a member of this notebook");
        }

        NotebookMembership membership = new NotebookMembership();
        membership.setId(id);
        membership.setNotebook(notebook);
        membership.setUser(user);
        membership.setRole(role);

        return notebookMembershipRepository.save(membership);
    }

}
