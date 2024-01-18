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
@Table(name = "enseignant")
public class Enseignant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enseignant_id")
    private int id;

    private String nom;

    private String prenom;

    private int age;

    private String grade;

    private Boolean profPrincipal = false;


    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;

    @ManyToMany
    @JoinTable(name = "enseignant_matiere",
    joinColumns = @JoinColumn(name = "enseignant_id"),
            inverseJoinColumns = @JoinColumn(name = "matiere_id")
    )
    private List<Matiere> matiereList = new ArrayList<>();

    @OneToMany(mappedBy = "enseignant", fetch = FetchType.LAZY)
    private List<Classe> classeList = new ArrayList<>();



}
