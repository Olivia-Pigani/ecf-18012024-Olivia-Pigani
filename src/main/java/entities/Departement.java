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
@Table(name = "departement")
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "departement_id")
    private int id;

    private String nom;

    @OneToMany(mappedBy = "departement",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Enseignant> enseignantList = new ArrayList<>();

    @OneToMany(mappedBy = "departement",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Classe> classeList = new ArrayList<>();

    @OneToMany(mappedBy = "departement",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Etudiant> etudiantList = new ArrayList<>();


}
