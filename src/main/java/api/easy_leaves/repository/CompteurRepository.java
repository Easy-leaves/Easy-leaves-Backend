package api.easy_leaves.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import api.easy_leaves.model.Compteur;

/**
 * @author Theo
 */
public interface CompteurRepository extends JpaRepository<Compteur, Integer> {
}
