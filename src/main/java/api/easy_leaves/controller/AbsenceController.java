package api.easy_leaves.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.easy_leaves.model.Absence;
import api.easy_leaves.services.AbsenceService;

/**
 * @author Theo
 */
@RestController
@RequestMapping("/absences")
public class AbsenceController {
	private final AbsenceService absenceService;

	/**
	 * Constructeur
	 * @param absenceService
	 */
	public AbsenceController(AbsenceService absenceService) {
		this.absenceService = absenceService;
	}

	/**
	 * Récupérer toutes les absences
	 * localhost:8080/absences
	 * @return Liste des absences
	 */
	@GetMapping
	public List<Absence> obtenirToutesLesAbsences() {
	    return absenceService.getAllAbsences();
	}
	
	/**
	 * Récupérer une absence par ID
	 * localhost:8080/absences/{id}
	 * @param id Identifiant de l'absence
	 * @return Absence correspondant à l'ID
	 */
	@GetMapping("/{id}")
	public Absence obtenirAbsenceParId(@PathVariable int id) {
	    Absence absence = absenceService.getAbsenceById(id);
	    return absence;
	}
	
	/**
	 * Créer une nouvelle absence
	 * localhost:8080/absences/add
	 * @param absence Objet Absence à créer
	 */
	@PostMapping("/add")
	public Absence creerAbsence(@RequestBody Absence absence) {
	    Absence nouvelleAbsence = absenceService.createAbsence(absence);
	    return nouvelleAbsence;
	}
	
	/**
	 * Mettre à jour une absence existante
	 * localhost:8080/absences/update/{id}
	 * @param id Identifiant de l'absence à mettre à jour
	 * @param absenceDetails Détails de la mise à jour
	 */
	@PutMapping("/update/{id}")
	public Absence mettreAJourAbsence(@PathVariable int id, @RequestBody Absence absenceDetails) {
	    Absence absenceMiseAJour = absenceService.updateAbsence(id, absenceDetails);
	    return absenceMiseAJour;
	}
	
	/**
	 * Supprimer une absence
	 * localhost:8080/absences/delete/{id}
	 * @param id Identifiant de l'absence à supprimer
	 * @return Message de confirmation
	 */
	@DeleteMapping("/delete/{id}")
	public void supprimerAbsence(@PathVariable int id) {
	    absenceService.deleteAbsence(id);
	}
}
