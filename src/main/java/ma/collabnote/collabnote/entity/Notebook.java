package ma.collabnote.collabnote.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "notebook")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Notebook {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_notebook")
    private UUID idNB;
    @NotBlank
    @Column(nullable = false)
    private String title;
}
