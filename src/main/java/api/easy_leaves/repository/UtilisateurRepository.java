package api.easy_leaves.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import api.easy_leaves.enums.Role;
import api.easy_leaves.enums.TypeCompteur;
import api.easy_leaves.model.Departement;
import api.easy_leaves.model.Utilisateur;

/**
 * @author Theo
 */
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>{
	
	/**
	 * Trouver un utilisateur par son nom
	 * @param nom
	 * @return
	 */
	Optional<Utilisateur> findByNom(String nom);
	
	/**
	 * Trouver un utilisateur par son prenom
	 * @param prenom
	 * @return
	 */
	Optional<Utilisateur> findByPrenom(String prenom);
	
	/**
	 * Trouver un utilisateur par email
	 * @param email
	 * @return
	 */
	Optional<Utilisateur> findByEmail(String email);
	
	/**
	 * Trouver tous les utilisateurs par rôle
	 * @param role
	 * @return
	 */
	List<Utilisateur> findByRole(Role role);
	
	/**
	 * Trouver les utilisateurs d'un département donné
	 * @param departementId
	 * @return
	 */
	List<Utilisateur> findByDepartement(Departement departement);
	
	/**
	 * Compter les utilisateurs d'un rôle spécifique dans un département
	 * @param role
	 * @param departementId
	 * @return
	 */
	Long countByRoleAndDepartement(Role role, Departement departement);
	
	/**
	 * Charger un utilisateur avec ses absences
	 * @param id
	 * @return
	 */
	@Query("SELECT u FROM Utilisateur u JOIN FETCH u.absenceUtilisateur WHERE u.idUtilisateur = :id")
	Utilisateur findByIdWithAbsences(int id);
	
	/**
	 * Trouver les utilisateurs avec un compteur spécifique (relation avec Compteur)
	 * @param type
	 * @return
	 */
	@Query("SELECT u FROM Utilisateur u JOIN u.compteurUtilisateur c WHERE c.typeCompteur = :type")
	List<Utilisateur> findByCompteurType(TypeCompteur type);
}
