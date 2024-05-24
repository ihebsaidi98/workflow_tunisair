package tn.tunisair.workfow.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.tunisair.workfow.Entities.Changement;
import tn.tunisair.workfow.Entities.Incident;
import tn.tunisair.workfow.Interfaces.ChangementInterface;
import tn.tunisair.workfow.Repositories.ChangementRepository;
import tn.tunisair.workfow.Repositories.IncidentRepository;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ChangementService implements ChangementInterface {

    private final ChangementRepository changementRepository;
    private final IncidentRepository incidentRepository;


    @Override
    public Changement addChangement(Changement changement) {
        return changementRepository.save(changement);
    }

    @Override
    public Changement findChangementById(Integer id) {
        return changementRepository.findById(id).orElse(null);
    }

    @Override
    public List<Changement> findAllChangements() {
        return changementRepository.findAll();
    }

    @Override
    public void deleteChangement(Integer id) {
        changementRepository.deleteById(id);
    }

    @Override
    public Changement updateChangement(Integer id, Changement changementDetails) {
        if (changementRepository.existsById(id)) {
            changementDetails.setId(id);
            return changementRepository.save(changementDetails);
        } else {
            // Handle error: Changement not found
            return null;
        }
    }

    public Changement addChangementToIncident(Integer incidentId, Changement changement) {
        Incident incident = incidentRepository.findById(incidentId).orElse(null);
        if (incident != null) {
            changement.setIncident(incident);
            return changementRepository.save(changement);
        } else {
            return null;
        }
    }

    public List<Changement> getAllChangementsByIncidentId(Integer incidentId) {
        Incident incident = incidentRepository.findById(incidentId).orElse(null);
        if (incident != null) {
            return incident.getChangements();
        } else {
            return Collections.emptyList();
        }
    }

    public void deleteChangementFromIncident(Integer incidentId, Integer changementId) {
        Incident incident = incidentRepository.findById(incidentId).orElse(null);
        if (incident != null) {
            List<Changement> changements = incident.getChangements();
            changements.removeIf(changement -> changement.getId().equals(changementId));
            incidentRepository.save(incident);
        }
    }

    public Changement updateChangementInIncident(Integer incidentId, Integer changementId, Changement updatedChangement) {
        Incident incident = incidentRepository.findById(incidentId).orElse(null);
        if (incident != null) {
            List<Changement> changements = incident.getChangements();
            for (Changement changement : changements) {
                if (changement.getId().equals(changementId)) {
                    // Update changement details
                    changement.setDescription(updatedChangement.getDescription());
                    changement.setStatutChangement(updatedChangement.getStatutChangement());
                    changement.setEvenementChangement(updatedChangement.getEvenementChangement());
                    changement.setDateCreation(updatedChangement.getDateCreation());
                    changement.setDateDebutPrevu(updatedChangement.getDateDebutPrevu());
                    changement.setDateFinPrevu(updatedChangement.getDateFinPrevu());
                    changement.setPriorite(updatedChangement.getPriorite());
                    changement.setCategorie(updatedChangement.getCategorie());
                    changement.setCommentaire(updatedChangement.getCommentaire());
                    changement.setFichier(updatedChangement.getFichier());
                    changement.setMotif(updatedChangement.getMotif());
                    changement.setEtapesMiseEnOeuvre(updatedChangement.getEtapesMiseEnOeuvre());
                    changement.setActionsCorrectives(updatedChangement.getActionsCorrectives());
                    changement.setEvaluationRisques(updatedChangement.getEvaluationRisques());
                    changement.setImpactUtilisateurs(updatedChangement.getImpactUtilisateurs());
                    changement.setHistoriqueModifications(updatedChangement.getHistoriqueModifications());
                    changement.setResponsable(updatedChangement.getResponsable());
                    // Other fields

                    return changementRepository.save(changement);
                }
            }
        }
        return null;
    }



}
