package tn.tunisair.workfow.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.tunisair.workfow.Entities.Incident;
import tn.tunisair.workfow.Entities.StatutIncident;

import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Integer> {
    List<Incident> findByStatutIncident(StatutIncident statutIncident);

    List<Incident> findByCategorie(String categorie);

}
