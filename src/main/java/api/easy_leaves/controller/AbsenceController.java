	package api.easy_leaves.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.easy_leaves.dto.AbsenceDTO;
import api.easy_leaves.enums.Statut;
import api.easy_leaves.errors.IncoherenceDateError;
import api.easy_leaves.enums.TypeAbsence;
import api.easy_leaves.errors.DataBaseError;
import api.easy_leaves.model.Absence;
import api.easy_leaves.model.Utilisateur;
import api.easy_leaves.services.AbsenceService;
import api.easy_leaves.services.UtilisateurService;

/**
 * @author Driss
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/absences")
public class AbsenceController {
	private final AbsenceService absenceService;
	private final UtilisateurService utilisateurService;

	/**
	 * Constructeur
	 * @param absenceService
	 */
	public AbsenceController(AbsenceService absenceService, UtilisateurService utilisateurService) {
		this.absenceService = absenceService;
		this.utilisateurService = utilisateurService;
	}	 

	/**
	 * Récupérer toutes les absences
	 * localhost:8080/absences
	 * @return Liste des absences
	 */
	@GetMapping
	public List<AbsenceDTO> obtenirToutesLesAbsences() {
		return absenceService.getAllAbsences().stream()
				.map(AbsenceDTO::convertToDTO)
				.collect(Collectors.toList());
	}
	
	/**
	 * Récupérer une absence par ID
	 * localhost:8080/absences/{id}
	 * @param id Identifiant de l'absence
	 * @return Absence correspondant à l'ID
	 */
	@GetMapping("/{id}")
	public AbsenceDTO obtenirAbsenceParId(@PathVariable("id") int id) {
		return AbsenceDTO.convertToDTO(absenceService.getAbsenceById(id));
	}
	
	/**
	 * Créer une nouvelle absence
	 * localhost:8080/absences/add
	 * @param absence Objet Absence à créer
	 */
	@PreAuthorize("hasAnyAuthority('MANAGER', 'EMPLOYE', 'ADMINISTRATEUR')")
	@PostMapping("/add")
	public Absence creerAbsence(@RequestBody Absence absence) {
		System.out.println("Nouvelle absence reçue : " + absence);		
		System.out.println("Utilisateur associé à l'absence : " + absence.getUtilisateur());
	    return absenceService.createAbsence(absence);
	}
	
	/**
	 * Mettre à jour une absence existante
	 * localhost:8080/absences/update/{id}
	 * @param id Identifiant de l'absence à mettre à jour
	 * @param absenceDetails Détails de la mise à jour
	 */
	@PutMapping("/update/{id}")
	public ResponseEntity<?> mettreAJourAbsence(@PathVariable int id, @RequestBody Absence absenceDetails) {
		try {
	        Absence updatedAbsence = absenceService.updateAbsence(id, absenceDetails);
	        return ResponseEntity.ok(updatedAbsence);
	    } catch (IncoherenceDateError e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}
	
	/**
	 * Mettre à jour le statut d'une absence existante
	 * localhost:8080/absences/update/{id}/statut
	 * @param id Identifiant de l'absence à mettre à jour
	 * @param absenceStatut le statut à mettre a jour
	 */
	@PutMapping("/update/{id}/statut")
	public Absence mettreAJourStatutAbsence(@PathVariable int id, @RequestBody String absenceStatut) {
	    return absenceService.updateAbsenceStatut(id, Statut.valueOf(absenceStatut));
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
	
	/**
	 * Récupérer toutes les absences par statut.
	 * @param statut Le statut des absences à récupérer.
	 * @return Liste des absences avec le statut donné.
	 */
	@GetMapping("/statut/{statut}")
	public List<AbsenceDTO> obtenirAbsencesParStatut(@PathVariable String statut) {
		return absenceService.getAbsencesByStatut(Statut.valueOf(statut)).stream()
				.map(absence -> {
					AbsenceDTO dto = AbsenceDTO.convertToDTO(absence);
					
					Utilisateur utilisateur = utilisateurService.getUtilisateurById(dto.getUtilisateurId());
					if(utilisateur != null) {
						dto.setUtilisateurNom(utilisateur.getNom());
					}
		                
		            return dto;
				})
				.collect(Collectors.toList());
	}
	
	/**
	 * Récupérer toutes les absences par statut.
	 * @param statut Le statut des absences à récupérer.
	 * @return Liste des absences avec le statut donné.
	 */
	@GetMapping("/type/{type}")
	public List<AbsenceDTO> obtenirAbsencesParType(@PathVariable String type) {
		return absenceService.getAbsencesByType(TypeAbsence.valueOf(type)).stream()
				.map(absence -> {
					AbsenceDTO dto = AbsenceDTO.convertToDTO(absence);		                
		            return dto;
				})
				.collect(Collectors.toList());
	}

	/**
	 * Récupérer toutes les absences dans une plage de dates.
	 * @param startDate Date de début de la plage.
	 * @param endDate Date de fin de la plage.
	 * @return Liste des absences dans la plage de dates donnée.
	 */
	@GetMapping("/plage/{startDate}/{endDate}")
	public List<AbsenceDTO> obtenirAbsencesParPlage(@RequestParam String startDate, @RequestParam String endDate) {
		// Convertir les dates reçues en objets Date (à ajuster selon votre format de date)
		Date start = Date.valueOf(startDate);
		Date end = Date.valueOf(endDate);
		return absenceService.getAbsencesByDateRange(start, end).stream()
				.map(AbsenceDTO::convertToDTO)
				.collect(Collectors.toList());
	}

	/**
	 * Compter le nombre d'absences pour un utilisateur et un statut donné.
	 * @param utilisateurId L'ID de l'utilisateur pour lequel compter les absences.
	 * @param statut Le statut des absences à compter.
	 * @return Le nombre d'absences pour l'utilisateur et le statut donné.
	 */
	@GetMapping("/compte/{utilisateurId}/{statut}")
	public Long compterAbsences(@RequestParam int utilisateurId, @RequestParam String statut) {
		Utilisateur utilisateur = new Utilisateur(); 
		utilisateur.setIdUtilisateur(utilisateurId);
		return absenceService.countAbsencesByUtilisateurAndStatut(utilisateur, Statut.valueOf(statut));
	}
	
	/**
     * Récupérer toutes les absences associées à un utilisateur spécifique par son identifiant.
     * Les absences sont converties en objets DTO (Data Transfer Object) pour faciliter le transfert des données.
     *
     * @param id L'identifiant de l'utilisateur pour lequel récupérer les absences.
     * @return Une liste d'objets {@link AbsenceDTO} représentant les absences de l'utilisateur spécifié.
     */
	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/utilisateur/{id}")
    public List<AbsenceDTO> getAbsencesByUtilisateurId(@PathVariable int id) {
        return absenceService.getAbsencesByUtilisateurId(id);
    }
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/rtt-employeur/add")
	public ResponseEntity<?> addRTTEmployeur(@RequestBody Absence absence) {
	    LocalDate dateDebut = absence.getDateDebut().toInstant()
	                                 .atZone(ZoneId.systemDefault())
	                                 .toLocalDate();
	    LocalDate dateFin = absence.getDateFin().toInstant()
	                               .atZone(ZoneId.systemDefault())
	                               .toLocalDate();

	    // Calcul du nombre de jours ouvrés pour cette nouvelle absence
	    long newAbsenceDays = absenceService.countWorkingDays(dateDebut, dateFin);

	    // Récupération du total des jours RTT employeur déjà posés cette année
	    int year = dateDebut.getYear();
	    List<Absence> rttEmployeurAbsences = absenceService.getRTTEmployeurByYear(year);
	    long totalExistingRttDays = rttEmployeurAbsences.stream()
	        .mapToLong(a -> absenceService.countWorkingDays(
	            a.getDateDebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
	            a.getDateFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
	        ))
	        .sum();

	    // Vérification du quota de 5 jours
	    if (totalExistingRttDays + newAbsenceDays > 5) {
	        return ResponseEntity.badRequest().body("Le nombre total de jours RTT employeur dépasse la limite annuelle de 5 jours.");
	    }

	    Absence newAbsence = absenceService.createAbsence(absence);
	    return ResponseEntity.ok(newAbsence);
	}
}