package tn.tunisair.workfow.Interfaces;

import tn.tunisair.workfow.Entities.Changement;
import tn.tunisair.workfow.Entities.Incident;
import tn.tunisair.workfow.Entities.StatutIncident;

import java.util.List;

public interface IncidentInterface {
    Incident ajouterIncident(Incident incident );


    Incident findIncidentById( Integer id ) ;

    List<Incident> findAllIncidents();



    void deleteIncident( Integer id ) ;


    Incident updateIncident( Integer id,Incident incidentDetails) ;

     void archiveResolvedIncidents() ;

    List<Incident> getIncidentsByStatut(StatutIncident statut);

     void closeIncident(Integer id) ;
     List<Incident> getIncidentsByCategory(String categorie);


    }

