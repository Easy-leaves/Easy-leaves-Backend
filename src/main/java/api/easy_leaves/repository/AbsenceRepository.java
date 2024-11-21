package api.easy_leaves.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import api.easy_leaves.model.Absence;

/**
 * @author Theo
 */
public interface AbsenceRepository extends JpaRepository<Absence, Integer>{
}
