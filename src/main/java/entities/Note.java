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

    @OneToMany
    private List<Matiere> matiereList = new ArrayList<>();

    @OneToMany(mappedBy = "note",fetch = FetchType.LAZY)
    private List<Etudiant> etudiantList = new ArrayList<>();




}
