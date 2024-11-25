package api.easy_leaves.services;

import api.easy_leaves.errors.DataBaseError;
import api.easy_leaves.errors.IncoherenceDateError;

import api.easy_leaves.model.Absence;
import api.easy_leaves.repository.AbsenceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

/**
 * Service pour la gestion des absences.
 * Ce service fournit les méthodes nécessaires pour manipuler les données des absences dans la base.
 * 
 * @author Driss
 */
@Service
public class AbsenceService {
	
	@Autowired
	private AbsenceRepository absenceRepository;
	
	/**
	 * Récupérer toutes les absences.
	 * 
	 * @return Liste des absences disponibles.
	 */
	public List<Absence> getAllAbsences() {
	    return absenceRepository.findAll();
	}
	
	/**
	 * Récupérer une absence par son identifiant.
	 * 
	 * @param id Identifiant unique de l'absence.
	 * @return L'absence correspondante.
	 * @throws DataBaseError Si aucune absence n'est trouvée pour l'identifiant donné.
	 */
	public Absence getAbsenceById(int id) {
	    return absenceRepository.findById(id).orElseThrow(() -> new DataBaseError("Absence introuvable"));
	}
	
	/**
	 * Créer une nouvelle absence.
	 * 
	 * @param absence Objet absence contenant les informations à enregistrer.
	 * @return L'absence nouvellement créée.
	 */
	public Absence createAbsence(Absence absence) {
	    return absenceRepository.save(absence);
	}
	
	/**
	 * Mettre à jour une absence existante.
	 * 
	 * @param id Identifiant de l'absence à mettre à jour.
	 * @param absenceDetails Objet contenant les nouvelles informations de l'absence.
	 * @return L'absence mise à jour.
	 * @throws RuntimeException Si l'absence à mettre à jour n'existe pas.
	 * @throws IncoherenceDateError Si les données de date de la mise à jour ne sont pas cohérentes.
	 */
	public Absence updateAbsence(int id, Absence absenceDetails) {
		
		/*
		 * La date de fin ne doit pas être inférieure à la date de début.
		 */
		if(absenceDetails.getDateFin().before(absenceDetails.getDateDebut())) {
			throw new IncoherenceDateError("La date de début ne peut pas être inférieure à la date de fin");
		}
		
		/*
		 * La date de début ne doit pas être inférieure à aujourd'hui.
		 */
		if(absenceDetails.getDateDebut().before(Date.from(ZonedDateTime.now().toInstant()))) {
			throw new IncoherenceDateError("La date de début ne peut pas être inférieure à la date de fin");
		}
		
		Absence absence = getAbsenceById(id);
		absence.setDateDebut(absenceDetails.getDateDebut());
		absence.setDateFin(absenceDetails.getDateFin());
		absence.setType(absenceDetails.getType());
		absence.setStatut(absenceDetails.getStatut());
		absence.setMotif(absenceDetails.getMotif());
		return absenceRepository.save(absence);
	}
	
	/**
	 * Supprimer une absence.
	 * 
	 * @param id Identifiant de l'absence à supprimer.
	 */
	public void deleteAbsence(int id) {
	    absenceRepository.deleteById(id);
	}
}
