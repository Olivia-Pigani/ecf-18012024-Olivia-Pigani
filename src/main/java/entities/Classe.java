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
@Table(name = "classe")
public class Classe {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classe_id")
    private int id;


    private String nom;


    private String niveau;

    @ManyToOne
    private Departement departement;

    @ManyToOne
    @JoinColumn(name = "emploi_du_tps")
    private EmploiDuTemps emploiDuTemps;

    @ManyToOne
    @JoinColumn(name = "enseignant_id")
    private Enseignant enseignant;

    @OneToMany(mappedBy = "classe",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Etudiant> etudiantList = new ArrayList<>();


}
