package api.easy_leaves.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.easy_leaves.model.Departement;

/**
 * @author Theo
 */
@Repository
public interface DepartementRepository extends JpaRepository<Departement, Integer> {
}
