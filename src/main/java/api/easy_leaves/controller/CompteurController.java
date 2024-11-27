package api.easy_leaves.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.easy_leaves.dto.CompteurDTO;
import api.easy_leaves.enums.TypeCompteur;
import api.easy_leaves.model.Compteur;
import api.easy_leaves.model.Utilisateur;
import api.easy_leaves.services.CompteurService;

/**
 * @author Theo
 */
@RestController
@RequestMapping("/compteurs")
public class CompteurController {
	private final CompteurService compteurService;
	
	/**
	 * Constructeur
	 * @param absenceService
	 */
	public CompteurController(CompteurService compteurService) {
		this.compteurService = compteurService;
	}
	
	/**
	 * Récupérer tous les compteurs
	 * localhost:8080/compteurs
	 * @return Liste des compteurs
	 */
	@GetMapping
	public List<CompteurDTO> obtenirTousLesCompteurs() {
		return compteurService.getAllCompteurs().stream()
				.map(CompteurDTO::convertToDTO)
				.collect(Collectors.toList());
	}
	
	/**
	 * Récupérer un compteur par ID
	 * localhost:8080/compteurs/{id}
	 * @param id Identifiant du compteur
	 * @return Compteur correspondant à l'ID
	 */
	@GetMapping("/{id}")
	public CompteurDTO obtenirCompteurParId(@PathVariable int id) {
	    return CompteurDTO.convertToDTO(compteurService.getCompteurById(id));
	}
	
	/**
	 * Créer un nouveau compteur
	 * localhost:8080/compteurs/add
	 * @param compteur Objet Compteur à créer
	 * @return Compteur créé
	 */
	@PostMapping("/add")
	public Compteur creerCompteur(@RequestBody Compteur compteur) {
	    return compteurService.createCompteur(compteur);
	}
	
	/**
	 * Mettre à jour un compteur existant
	 * localhost:8080/compteurs/update/{id}
	 * @param id Identifiant du compteur à mettre à jour
	 * @param compteurDetails Détails de la mise à jour
	 * @return Compteur mis à jour
	 */
	@PutMapping("/update/{id}")
	public Compteur mettreAJourCompteur(@PathVariable int id, @RequestBody Compteur compteurDetails) {
	    return compteurService.updateCompteur(id, compteurDetails);
	}
	
	/**
	 * Supprimer un compteur
	 * localhost:8080/compteurs/delete/{id}
	 * @param id Identifiant du compteur à supprimer
	 * @return Message de confirmation
	 */
	@DeleteMapping("/delete/{id}")
	public void supprimerCompteur(@PathVariable int id) {
	    compteurService.deleteCompteur(id);
	}
	
	
	/**
     * Récupérer les compteurs d'un utilisateur donné.
     * Exemple : GET localhost:8080/compteurs/utilisateur/{id}
     *
     * @param utilisateur Objet utilisateur.
     * @return Liste des compteurs de l'utilisateur.
     */
    @GetMapping("/utilisateur/{id}")
    public List<CompteurDTO> obtenirCompteursParUtilisateur(@PathVariable Utilisateur utilisateur) {
        return compteurService.findByUtilisateur(utilisateur).stream()
                .map(CompteurDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer les compteurs pour une année donnée.
     * Exemple : GET localhost:8080/compteurs/annee/2024
     *
     * @param annee Année.
     * @return Liste des compteurs pour l'année donnée.
     */
    @GetMapping("/annee/{annee}")
    public List<CompteurDTO> obtenirCompteursParAnnee(@RequestParam int annee) {
        return compteurService.findByAnnee(annee).stream()
                .map(CompteurDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer les compteurs par type et utilisateur.
     * Exemple : GET localhost:8080/compteurs/type-utilisateur/{typeCompteur}/{utilisateur}
     *
     * @param typeCompteur Type de compteur.
     * @param utilisateur  Utilisateur.
     * @return Liste des compteurs correspondant au type et à l'utilisateur.
     */
    @GetMapping("/type-utilisateur/{typeCompteur}/{utilisateur}")
    public List<CompteurDTO> obtenirCompteursParTypeEtUtilisateur(@RequestParam TypeCompteur typeCompteur, @RequestParam Utilisateur utilisateur) {
        return compteurService.findByTypeCompteurAndUtilisateur(typeCompteur, utilisateur).stream()
                .map(CompteurDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Compter le nombre de compteurs d'un certain type pour une année donnée.
     * Exemple : GET localhost:8080/compter/{typeCompteur}/{annee}
     *
     * @param typeCompteur Type de compteur.
     * @param annee        Année.
     * @return Nombre de compteurs trouvés.
     */
    @GetMapping("/compter/{typeCompteur}/{annee}")
    public Long compterCompteursParTypeEtAnnee(@RequestParam TypeCompteur typeCompteur, @RequestParam int annee) {
        return compteurService.countByTypeCompteurAndAnnee(typeCompteur, annee);
    }

    /**
     * Récupérer les compteurs pour un utilisateur donné dans une plage d'années.
     * Exemple : GET localhost:8080/compteurs/plage-annees/{utilisateur}/{startYear}/{endYear}
     *
     * @param utilisateur Utilisateur.
     * @param startYear   Année de début.
     * @param endYear     Année de fin.
     * @return Liste des compteurs pour la plage d'années donnée.
     */
    @GetMapping("/plage-annees/{utilisateur}/{startYear}/{endYear}")
    public List<CompteurDTO> obtenirCompteursParPlageDAnnees(@RequestParam Utilisateur utilisateur, @RequestParam int startYear, @RequestParam int endYear) {
        return compteurService.findByUtilisateurAndAnneeBetween(utilisateur, startYear, endYear).stream()
                .map(CompteurDTO::convertToDTO)
                .collect(Collectors.toList());
    }
}
