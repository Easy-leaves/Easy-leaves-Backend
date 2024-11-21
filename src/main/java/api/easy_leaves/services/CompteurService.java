package api.easy_leaves.services;

import api.easy_leaves.model.Compteur;
import api.easy_leaves.repository.CompteurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service pour la gestion des compteurs.
 * Fournit des méthodes pour interagir avec les compteurs dans la base de données.
 * 
 * @author Driss
 */
@Service
public class CompteurService {

    @Autowired
    private CompteurRepository compteurRepository;

    /**
     * Récupérer tous les compteurs.
     * 
     * @return Liste des compteurs disponibles.
     */
    public List<Compteur> getAllCompteurs() {
        return compteurRepository.findAll();
    }

    /**
     * Récupérer un compteur par son identifiant.
     * 
     * @param id Identifiant unique du compteur.
     * @return Le compteur correspondant.
     * @throws RuntimeException Si aucun compteur n'est trouvé pour l'identifiant donné.
     */
    public Compteur getCompteurById(int id) {
        return compteurRepository.findById(id).orElseThrow(() -> new RuntimeException("Compteur introuvable"));
    }

    /**
     * Créer un nouveau compteur.
     * 
     * @param compteur Objet contenant les informations du compteur à enregistrer.
     * @return Le compteur nouvellement créé.
     */
    public Compteur createCompteur(Compteur compteur) {
        return compteurRepository.save(compteur);
    }

    /**
     * Mettre à jour un compteur existant.
     * 
     * @param id Identifiant du compteur à mettre à jour.
     * @param compteurDetails Objet contenant les nouvelles informations du compteur.
     * @return Le compteur mis à jour.
     * @throws RuntimeException Si le compteur à mettre à jour n'existe pas.
     */
    public Compteur updateCompteur(int id, Compteur compteurDetails) {
        Compteur compteur = getCompteurById(id);
        compteur.setAnnee(compteurDetails.getAnnee());
        compteur.setTypeCompteur(compteurDetails.getTypeCompteur());
        return compteurRepository.save(compteur);
    }

    /**
     * Supprimer un compteur.
     * 
     * @param id Identifiant du compteur à supprimer.
     */
    public void deleteCompteur(int id) {
        compteurRepository.deleteById(id);
    }
}
