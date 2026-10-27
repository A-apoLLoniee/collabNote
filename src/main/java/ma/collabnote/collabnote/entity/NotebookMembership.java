package ma.collabnote.collabnote.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notebook_membership")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotebookMembership {
    @EmbeddedId
    private NotebookMembershipID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId(value = "idUser")
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId(value = "idNB")
    @JoinColumn(name = "idNB")
    private Notebook notebook;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
