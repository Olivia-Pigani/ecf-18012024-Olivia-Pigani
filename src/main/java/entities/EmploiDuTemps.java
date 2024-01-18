package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "emploi_du_tps")
public class EmploiDuTemps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emploi_du_tps_id")
    private int id;

    private LocalDate jourEtHeure;

    @OneToMany(mappedBy = "emploiDuTemps", fetch = FetchType.LAZY)
    private List<Classe> classeList = new ArrayList<>();

    @OneToMany(mappedBy = "emploiDuTemps", fetch = FetchType.LAZY)
    private List<Matiere> matiereList = new ArrayList<>();



}
