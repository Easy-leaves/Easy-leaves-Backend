package api.easy_leaves.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.easy_leaves.model.Compteur;
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
    public List<Compteur> obtenirTousLesCompteurs() {
        return compteurService.getAllCompteurs();
    }

    /**
     * Récupérer un compteur par ID
     * localhost:8080/compteurs/{id}
     * @param id Identifiant du compteur
     * @return Compteur correspondant à l'ID
     */
    @GetMapping("/{id}")
    public Compteur obtenirCompteurParId(@PathVariable int id) {
        Compteur compteur = compteurService.getCompteurById(id);
        return compteur;
    }

    /**
     * Créer un nouveau compteur
     * localhost:8080/compteurs/add
     * @param compteur Objet Compteur à créer
     * @return Compteur créé
     */
    @PostMapping("/add")
    public Compteur creerCompteur(@RequestBody Compteur compteur) {
        Compteur nouveauCompteur = compteurService.createCompteur(compteur);
        return nouveauCompteur;
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
        Compteur compteurMisAJour = compteurService.updateCompteur(id, compteurDetails);
        return compteurMisAJour;
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
}
