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
public class Probleme implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String description;
    String impact;
    @Enumerated(EnumType.STRING)
    StatutProbleme statutProbleme;
    @Enumerated(EnumType.STRING)
    Priorite priorite;
    Date dateCreation;
    Date dateResolution;
    byte[] fichier;



}
