package api.easy_leaves.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.easy_leaves.model.Compteur;

/**
 * @author Theo
 */
@Repository
public interface CompteurRepository extends JpaRepository<Compteur, Integer> {
}
