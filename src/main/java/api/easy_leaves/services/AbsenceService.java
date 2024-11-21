package api.easy_leaves.services;

import api.easy_leaves.model.Absence;
import api.easy_leaves.repository.AbsenceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsenceService {

    @Autowired
    private AbsenceRepository absenceRepository;

    public List<Absence> getAllAbsences() {
        return absenceRepository.findAll();
    }

    public Absence getAbsenceById(int id) {
        return absenceRepository.findById(id).orElseThrow(() -> new RuntimeException("Absence introuvable"));
    }

    public Absence createAbsence(Absence absence) {
        return absenceRepository.save(absence);
    }

    public Absence updateAbsence(int id, Absence absenceDetails) {
        Absence absence = getAbsenceById(id);
        absence.setDateDebut(absenceDetails.getDateDebut());
        absence.setDateFin(absenceDetails.getDateFin());
        absence.setType(absenceDetails.getType());
        absence.setStatut(absenceDetails.getStatut());
        absence.setMotif(absenceDetails.getMotif());
        return absenceRepository.save(absence);
    }

    public void deleteAbsence(int id) {
        absenceRepository.deleteById(id);
    }
}
