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
import org.springframework.web.bind.annotation.RestController;

import api.easy_leaves.dto.UtilisateurDTO;
import api.easy_leaves.model.Utilisateur;
import api.easy_leaves.services.UtilisateurService;

/**
 * @author Theo
 */
@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {
	private final UtilisateurService utilisateurService;
	
	/**
	 * Constructeur
	 * @param utilisateurService
	 */
	public UtilisateurController(UtilisateurService utilisateurService) {
	    this.utilisateurService = utilisateurService;
	}
	
	/**
	 * Récupérer tous les utilisateurs
	 * localhost:8080/utilisateurs
	 * @return Liste des utilisateurs
	 */
	@GetMapping
	public List<UtilisateurDTO> obtenirTousLesUtilisateurs() {
	    return utilisateurService.getAllUtilisateurs().stream()
	    		.map(UtilisateurDTO::convertToDTO)
	    		.collect(Collectors.toList());
	}
	
	/**
	 * Récupérer un utilisateur par ID
	 * localhost:8080/utilisateurs/{id}
	 * @param id Identifiant de l'utilisateur
	 * @return Utilisateur correspondant à l'ID
	 */
	@GetMapping("/{id}")
	public UtilisateurDTO obtenirUtilisateurParId(@PathVariable int id) {
	    return UtilisateurDTO.convertToDTO(utilisateurService.getUtilisateurById(id));
	}
	
	/**
	 * Créer un nouvel utilisateur
	 * localhost:8080/utilisateurs/add
	 * @param utilisateur Objet Utilisateur à créer
	 * @return Utilisateur créé
	 */
	@PostMapping("/add")
	public Utilisateur creerUtilisateur(@RequestBody Utilisateur utilisateur) {
	    return utilisateurService.createUtilisateur(utilisateur);
	}
	
	/**
	 * Mettre à jour un utilisateur existant
	 * localhost:8080/utilisateurs/update/{id}
	 * @param id Identifiant de l'utilisateur à mettre à jour
	 * @param utilisateurDetails Détails de la mise à jour
	 * @return Utilisateur mis à jour
	 */
	@PutMapping("/update/{id}")
	public Utilisateur mettreAJourUtilisateur(@PathVariable int id, @RequestBody Utilisateur utilisateurDetails) {
	    return utilisateurService.updateUtilisateur(id, utilisateurDetails);
	}
	
	/**
	 * Supprimer un utilisateur
	 * localhost:8080/utilisateurs/delete/{id}
	 * @param id Identifiant de l'utilisateur à supprimer
	 * @return Message de confirmation
	 */
	@DeleteMapping("/delete/{id}")
	public void supprimerUtilisateur(@PathVariable int id) {
	    utilisateurService.deleteUtilisateur(id);
	}
}
