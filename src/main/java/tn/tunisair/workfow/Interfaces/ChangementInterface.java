package tn.tunisair.workfow.Interfaces;

import tn.tunisair.workfow.Entities.Changement;

import java.util.List;

public interface ChangementInterface {
    Changement addChangement(Changement changement);

    Changement findChangementById(Integer id);

    List<Changement> findAllChangements();

    void deleteChangement(Integer id);

    Changement updateChangement(Integer id, Changement changementDetails);

     Changement addChangementToIncident(Integer incidentId, Changement changement) ;

    List<Changement> getAllChangementsByIncidentId(Integer incidentId);

     void deleteChangementFromIncident(Integer incidentId, Integer changementId) ;

    Changement updateChangementInIncident(Integer incidentId, Integer changementId, Changement updatedChangement);


    }
