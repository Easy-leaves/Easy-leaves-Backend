package api.easy_leaves.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.easy_leaves.model.Absence;

/**
 * @author Theo
 */
@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Integer>{
}
