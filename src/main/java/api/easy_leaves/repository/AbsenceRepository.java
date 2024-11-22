package api.easy_leaves.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.easy_leaves.enums.Statut;
import api.easy_leaves.enums.TypeAbsence;
import api.easy_leaves.model.Absence;
import api.easy_leaves.model.Utilisateur;

/**
 * @author Theo
 */
@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Integer>{
	
	/**
	 * Trouver toutes les absences par statut
	 * @param statut
	 * @return
	 */
	List<Absence> findByStatut(Statut statut);
	
	/**
	 * Trouver toutes les absences d'un utilisateur donné
	 * @param utilisateurId
	 * @return
	 */
	List<Absence> findByUtilisateur(Utilisateur utilisateur);
	
	/**
	 * Trouver les absences dans une plage de dates
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Absence> findByDateDebutBetween(Date startDate, Date endDate);
	
	/**
	 * Trouver les absences par type et statut
	 * @param type
	 * @param statut
	 * @return
	 */
	List<Absence> findByTypeAndStatut(TypeAbsence type, Statut statut);
	
	/**
	 * Compter les absences pour un utilisateur donné avec un certain statut
	 * @param utilisateurId
	 * @param statut
	 * @return
	 */
	Long countByUtilisateurAndStatut(Utilisateur utilisateur, Statut statut);
}
