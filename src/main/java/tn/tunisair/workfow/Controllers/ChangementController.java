package tn.tunisair.workfow.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.tunisair.workfow.Entities.Changement;
import tn.tunisair.workfow.Services.ChangementService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/changement")
@Tag(name = "Changement Management")
public class ChangementController {

    private final ChangementService changementService;

    @Operation(description = "Add changement")
    @PostMapping("/add")
    public Changement addChangement(@RequestBody Changement changement) {
        return changementService.addChangement(changement);
    }

    @Operation(description = "Find one changement")
    @GetMapping("/{id}")
    public Changement findChangementById(@PathVariable Integer id) {
        return changementService.findChangementById(id);
    }

    @Operation(description = "Find all changements")
    @GetMapping("/list")
    public List<Changement> getAllChangements() {
        return changementService.findAllChangements();
    }

    @Operation(description = "Delete changement")
    @DeleteMapping("/delete/{id}")
    public void deleteChangement(@PathVariable Integer id) {
        changementService.deleteChangement(id);
    }

    @Operation(description = "Update changement")
    @PutMapping("/update/{id}")
    public Changement updateChangement(@PathVariable Integer id, @RequestBody Changement changementDetails) {
        return changementService.updateChangement(id, changementDetails);
    }

    @Operation(description = "Add changement to incident")
    @PostMapping("/add/{incidentId}")
    public Changement addChangementToIncident(@PathVariable Integer incidentId, @RequestBody Changement changement) {
        return changementService.addChangementToIncident(incidentId, changement);
    }

    @Operation(description = "Get all changements by incidentId")
    @GetMapping("/incident/{incidentId}")
    public List<Changement> getAllChangementsByIncidentId(@PathVariable Integer incidentId) {
        return changementService.getAllChangementsByIncidentId(incidentId);
    }

    @Operation(description = "Delete changement from incident")
    @DeleteMapping("/incident/{incidentId}/delete/{changementId}")
    public void deleteChangementFromIncident(@PathVariable Integer incidentId, @PathVariable Integer changementId) {
        changementService.deleteChangementFromIncident(incidentId, changementId);
    }

    @Operation(description = "Update changement in incident")
    @PutMapping("/incident/{incidentId}/update/{changementId}")
    public Changement updateChangementInIncident(@PathVariable Integer incidentId, @PathVariable Integer changementId, @RequestBody Changement updatedChangement) {
        return changementService.updateChangementInIncident(incidentId, changementId, updatedChangement);
    }
}
