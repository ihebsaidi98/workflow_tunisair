package tn.tunisair.workfow.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
public class Incident implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String description;
    String impact;
    String categorie;
    String natureIncident;
    Date dateCreation;
    Date dateResolution;
    Date echeance;
    String historiqueActions;
    @Enumerated(EnumType.STRING)
    StatutIncident statutIncident;
    @Enumerated(EnumType.STRING)
    Priorite priorite;
    @Enumerated(EnumType.STRING)
    EvenementIncident evenementIncident;
    byte[] fichier;

    boolean archived; // Add archived field


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "incident", cascade = CascadeType.ALL)
    private List<Changement> changements;

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}
