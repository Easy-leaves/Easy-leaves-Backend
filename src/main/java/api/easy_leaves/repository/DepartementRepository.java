package api.easy_leaves.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import api.easy_leaves.model.Departement;

/**
 * @author Theo
 */
public interface DepartementRepository extends JpaRepository<Departement, Integer> {
}
