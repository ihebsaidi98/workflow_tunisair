package tn.tunisair.workfow.Services;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.tunisair.workfow.Entities.Incident;
import tn.tunisair.workfow.Entities.StatutIncident;
import tn.tunisair.workfow.Interfaces.IncidentInterface;
import tn.tunisair.workfow.Repositories.IncidentRepository;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
@Slf4j
public class IncidentService implements IncidentInterface {
    private final IncidentRepository incidentRepository;

    @Override
    public Incident ajouterIncident(Incident incident) {
        return incidentRepository.save(incident);
    }

    @Override
    public Incident findIncidentById(Integer id) {
        return incidentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Incident> findAllIncidents() {
        return incidentRepository.findAll();
    }

    @Override
    public void deleteIncident(Integer id) {
        incidentRepository.deleteById(id);
    }

    @Override
    public Incident updateIncident(Integer id, Incident incidentDetails) {
        if (incidentRepository.existsById(id)) {
            incidentDetails.setId(id);
            return incidentRepository.save(incidentDetails);
        } else {
            // Handle error: Incident not found
            return null;
        }
    }
    public void archiveResolvedIncidents() {
        List<Incident> resolvedIncidents = incidentRepository.findByStatutIncident(StatutIncident.RESOLU);
        for (Incident incident : resolvedIncidents) {
            incident.setArchived(true);
            incidentRepository.save(incident);
        }
    }
    public List<Incident> getIncidentsByStatut(StatutIncident statut) {
        return incidentRepository.findByStatutIncident(statut);
    }
    public void closeIncident(Integer id) {
        Incident incident = incidentRepository.findById(id).orElse(null);
        if (incident != null) {
            incident.setStatutIncident(StatutIncident.RESOLU);
            incidentRepository.save(incident);
        }
    }
    public List<Incident> getIncidentsByCategory(String categorie) {
        return incidentRepository.findByCategorie(categorie);
    }
}
