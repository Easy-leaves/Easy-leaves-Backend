package api.easy_leaves.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import api.easy_leaves.model.Utilisateur;

/**
 * @author Theo
 */
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>{
}
