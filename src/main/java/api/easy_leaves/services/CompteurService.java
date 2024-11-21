package api.easy_leaves.services;

import api.easy_leaves.model.Compteur;
import api.easy_leaves.repository.CompteurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteurService {

    @Autowired
    private CompteurRepository compteurRepository;

    public List<Compteur> getAllCompteurs() {
        return compteurRepository.findAll();
    }

    public Compteur getCompteurById(int id) {
        return compteurRepository.findById(id).orElseThrow(() -> new RuntimeException("Compteur introuvable"));
    }

    public Compteur createCompteur(Compteur compteur) {
        return compteurRepository.save(compteur);
    }

    public Compteur updateCompteur(int id, Compteur compteurDetails) {
        Compteur compteur = getCompteurById(id);
        compteur.setAnnee(compteurDetails.getAnnee());
        compteur.setTypeCompteur(compteurDetails.getTypeCompteur());
        return compteurRepository.save(compteur);
    }

    public void deleteCompteur(int id) {
        compteurRepository.deleteById(id);
    }
}
