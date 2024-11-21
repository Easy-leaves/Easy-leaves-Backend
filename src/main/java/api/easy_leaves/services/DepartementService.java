package api.easy_leaves.services;


import api.easy_leaves.model.Departement;
import api.easy_leaves.repository.DepartementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService {

    @Autowired
    private DepartementRepository departementRepository;

    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    public Departement getDepartementById(int id) {
        return departementRepository.findById(id).orElseThrow(() -> new RuntimeException("Département introuvable"));
    }

    public Departement createDepartement(Departement departement) {
        return departementRepository.save(departement);
    }

    public Departement updateDepartement(int id, Departement departementDetails) {
        Departement departement = getDepartementById(id);
        departement.setLibelle(departementDetails.getLibelle());
        return departementRepository.save(departement);
    }

    public void deleteDepartement(int id) {
        departementRepository.deleteById(id);
    }
}
