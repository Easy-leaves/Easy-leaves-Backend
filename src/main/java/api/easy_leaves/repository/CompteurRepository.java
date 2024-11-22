package api.easy_leaves.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.easy_leaves.enums.TypeCompteur;
import api.easy_leaves.model.Compteur;
import api.easy_leaves.model.Utilisateur;

/**
 * @author Theo
 */
@Repository
public interface CompteurRepository extends JpaRepository<Compteur, Integer> {
	
	/**
	 * Trouver tous les compteurs d'un utilisateur donné
	 * @param utilisateurId
	 * @return
	 */
	List<Compteur> findByUtilisateur(Utilisateur utilisateur);
	
	/**
	 * Trouver les compteurs pour une année spécifique
	 * @param annee
	 * @return
	 */
	List<Compteur> findByAnnee(int annee);
	
	/**
	 * Trouver les compteurs par type pour un utilisateur donné
	 * @param typeCompteur
	 * @param utilisateurId
	 * @return
	 */
	List<Compteur> findByTypeCompteurAndUtilisateur(TypeCompteur typeCompteur, Utilisateur utilisateur);
	
	/**
	 * Compter le nombre de compteurs d'un certain type pour une année donnée
	 * @param typeCompteur
	 * @param annee
	 * @return
	 */
	Long countByTypeCompteurAndAnnee(TypeCompteur typeCompteur, int annee);
	
	/**
	 * Trouver les compteurs pour un utilisateur donné dans une plage d'années
	 * @param utilisateurId
	 * @param startYear
	 * @param endYear
	 * @return
	 */
	List<Compteur> findByUtilisateurAndAnneeBetween(Utilisateur utilisateur, int startYear, int endYear);
}
