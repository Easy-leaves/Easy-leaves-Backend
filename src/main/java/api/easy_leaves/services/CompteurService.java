package api.easy_leaves.services;

import api.easy_leaves.enums.TypeCompteur;
import api.easy_leaves.errors.DataBaseError;
import api.easy_leaves.model.Compteur;
import api.easy_leaves.model.Utilisateur;
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
	 * @throws DataBaseError Si aucun compteur n'est trouvé pour l'identifiant donné.
	 */
	public Compteur getCompteurById(int id) {
	    return compteurRepository.findById(id).orElseThrow(() -> new DataBaseError("Compteur introuvable"));
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
	
	
	/**
     * Récupérer les compteurs pour un utilisateur donné.
     * 
     * @param utilisateur Objet utilisateur.
     * @return Liste des compteurs de l'utilisateur.
     */
    public List<Compteur> findByUtilisateur(Utilisateur utilisateur) {
        return compteurRepository.findByUtilisateur(utilisateur);
    }

    /**
     * Récupérer les compteurs pour une année donnée.
     * 
     * @param annee Année.
     * @return Liste des compteurs pour l'année donnée.
     */
    public List<Compteur> findByAnnee(int annee) {
        return compteurRepository.findByAnnee(annee);
    }

    /**
     * Récupérer les compteurs par type pour un utilisateur donné.
     * 
     * @param typeCompteur Type de compteur.
     * @param utilisateur  Utilisateur.
     * @return Liste des compteurs correspondant au type et à l'utilisateur.
     */
    public List<Compteur> findByTypeCompteurAndUtilisateur(TypeCompteur typeCompteur, Utilisateur utilisateur) {
        return compteurRepository.findByTypeCompteurAndUtilisateur(typeCompteur, utilisateur);
    }

    /**
     * Compter le nombre de compteurs d'un certain type pour une année donnée.
     * 
     * @param typeCompteur Type de compteur.
     * @param annee        Année.
     * @return Nombre de compteurs trouvés.
     */
    public Long countByTypeCompteurAndAnnee(TypeCompteur typeCompteur, int annee) {
        return compteurRepository.countByTypeCompteurAndAnnee(typeCompteur, annee);
    }

    /**
     * Récupérer les compteurs pour un utilisateur donné dans une plage d'années.
     * 
     * @param utilisateur Utilisateur.
     * @param startYear   Année de début.
     * @param endYear     Année de fin.
     * @return Liste des compteurs pour la plage d'années donnée.
     */
    public List<Compteur> findByUtilisateurAndAnneeBetween(Utilisateur utilisateur, int startYear, int endYear) {
        return compteurRepository.findByUtilisateurAndAnneeBetween(utilisateur, startYear, endYear);
    }
}
