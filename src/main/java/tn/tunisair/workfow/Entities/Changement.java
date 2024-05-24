package tn.tunisair.workfow.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
public class Changement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String description;
    @Enumerated(EnumType.STRING)
    StatutChangement statutChangement;
    @Enumerated(EnumType.STRING)
    EvenementChangement evenementChangement;
    Date dateCreation;
    Date dateDebutPrevu;
    Date dateFinPrevu;
    String priorite;
    String categorie;
    String commentaire;
    byte[] fichier;
    String motif;
    String etapesMiseEnOeuvre;
    String actionsCorrectives;
    String evaluationRisques;
    String impactUtilisateurs;
    String historiqueModifications;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User responsable;


    @ManyToOne
    @JoinColumn(name = "incident_id")
    private Incident incident;
}
