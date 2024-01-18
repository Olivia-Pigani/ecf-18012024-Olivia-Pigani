package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "matiere")
public class Matiere {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String intitule;

    private String description;

    private int dureeMin;

    private int coeff;

    @ManyToMany(mappedBy = "matiereList")
    private List<Enseignant> enseignantList;

    @ManyToOne
    private Note note;

    @ManyToOne
    @JoinColumn(name = "emploi_du_tps")
    private EmploiDuTemps emploiDuTemps;







}
