package api.easy_leaves.traitement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import api.easy_leaves.enums.Statut;
import api.easy_leaves.model.Absence;
import api.easy_leaves.repository.AbsenceRepository;

@Component
public class IncrementationTache {
	
	@Autowired
	private AbsenceRepository absenceRepository;
	
	/**
	 * Tâche planifiée pour changer le statut des absences de INITIALE à EN_ATTENTE_VALIDATION
	 * Exécutée tous les jours à minuit.
	 * Pour vérifier le fonctionnement mettre "0 * * * * *" qui met à jour toutes les minutes
	 */
	@Scheduled(cron = "0 0 0 * * *")
	public void updateAbsenceStatut() {
		List<Absence> absences = absenceRepository.findByStatut(Statut.INITIALE);
		
		for (Absence absence : absences) {
		    absence.setStatut(Statut.EN_ATTENTE_VALIDATION);
		}
		
		absenceRepository.saveAll(absences);
		
		System.out.println("Mise à jour des statuts effectuée !");
	}
}
