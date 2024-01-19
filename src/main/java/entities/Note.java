package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private int id;


    private String commentaire;

    private int valeur;

    @ManyToOne
    @JoinColumn(name = "matiere_id")
    private Matiere matiere;

   @ManyToOne
   @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;




}
