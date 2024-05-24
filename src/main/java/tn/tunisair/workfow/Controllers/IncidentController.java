package tn.tunisair.workfow.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.tunisair.workfow.Entities.Incident;
import tn.tunisair.workfow.Entities.StatutIncident;
import tn.tunisair.workfow.Services.IncidentService;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/incident")
@Tag(name = "Incident Management")
public class IncidentController {

    private final IncidentService incidentService;

    @Operation(description = "Add incident")
    @PostMapping("/add")
    public Incident ajouterIncident(@RequestBody Incident incident) {
        return incidentService.ajouterIncident(incident);
    }

    @Operation(description = "Find one incident")
    @GetMapping("/{id}")
    public Incident findIncidentById(@PathVariable Integer id) {
        return incidentService.findIncidentById(id);
    }

    @Operation(description = "Find all incidents")
    @GetMapping("/list")
    public List<Incident> getAllIncidents() {
        return incidentService.findAllIncidents();
    }

    @Operation(description = "Delete incident")
    @DeleteMapping("/delete/{id}")
    public void supprimerIncident(@PathVariable Integer id) {
        incidentService.deleteIncident(id);
    }

    @Operation(description = "Update incident")
    @PutMapping("/update/{id}")
    public Incident updateIncident(@PathVariable Integer id, @RequestBody Incident incidentDetails) {
        return incidentService.updateIncident(id, incidentDetails);
    }
    @Operation(description = "Archive resolved incidents")
    @PostMapping("/archiveResolved")
    public void archiveResolvedIncidents() {
        incidentService.archiveResolvedIncidents();
    }

    @GetMapping("/incident/byStatut")
    public List<Incident> getIncidentsByStatut(@RequestParam StatutIncident statut) {
        return incidentService.getIncidentsByStatut(statut);
    }

    @Operation(description = "Close incident")
    @PutMapping("/close/{id}")
    public void closeIncident(@PathVariable Integer id) {
        incidentService.closeIncident(id);
    }

    @Operation(description = "Get incidents by category")
    @GetMapping("/category/{categorie}")
    public List<Incident> getIncidentsByCategory(@PathVariable String categorie) {
        return incidentService.getIncidentsByCategory(categorie);
    }
}
