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
import api.easy_leaves.enums.Role;
import api.easy_leaves.enums.TypeCompteur;
import api.easy_leaves.model.Departement;
import api.easy_leaves.model.Utilisateur;
import api.easy_leaves.services.UtilisateurService;

/**
 * Contrôleur REST pour la gestion des utilisateurs.
 * Fournit des points d'accès pour effectuer des opérations CRUD et des recherches avancées sur les utilisateurs.
 * 
 * @author Theo
 */
@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {
	private final UtilisateurService utilisateurService;

	/**
	 * Constructeur pour injecter le service utilisateur.
	 *
	 * @param utilisateurService Service de gestion des utilisateurs.
	 */
	public UtilisateurController(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}

	/**
	 * Récupérer tous les utilisateurs.
	 * Endpoint : GET /utilisateurs
	 *
	 * @return Liste des utilisateurs sous forme de DTO.
	 */
	@GetMapping
	public List<UtilisateurDTO> obtenirTousLesUtilisateurs() {
		return utilisateurService.getAllUtilisateurs().stream()
				.map(UtilisateurDTO::convertToDTO)
				.collect(Collectors.toList());
	}

	/**
	 * Récupérer un utilisateur par son identifiant.
	 * Endpoint : GET /utilisateurs/{id}
	 *
	 * @param id Identifiant unique de l'utilisateur.
	 * @return DTO de l'utilisateur correspondant.
	 */
	@GetMapping("/{id}")
	public UtilisateurDTO obtenirUtilisateurParId(@PathVariable int id) {
		return UtilisateurDTO.convertToDTO(utilisateurService.getUtilisateurById(id));
	}

	/**
	 * Créer un nouvel utilisateur.
	 * Endpoint : POST /utilisateurs/add
	 *
	 * @param utilisateur Objet contenant les informations de l'utilisateur à créer.
	 * @return Identifiant de l'utilisateur nouvellement créé.
	 */
	@PostMapping("/add")
	public int creerUtilisateur(@RequestBody Utilisateur utilisateur) {
		Utilisateur nouvelUtilisateur = utilisateurService.createUtilisateur(utilisateur);
		return nouvelUtilisateur.getIdUtilisateur();
	}

	/**
	 * Mettre à jour un utilisateur existant.
	 * Endpoint : PUT /utilisateurs/update/{id}
	 *
	 * @param id Identifiant de l'utilisateur à mettre à jour.
	 * @param utilisateurDetails Objet contenant les nouvelles informations de l'utilisateur.
	 * @return Objet Utilisateur mis à jour.
	 */
	@PutMapping("/update/{id}")
	public Utilisateur mettreAJourUtilisateur(@PathVariable int id, @RequestBody Utilisateur utilisateurDetails) {
		return utilisateurService.updateUtilisateur(id, utilisateurDetails);
	}

	/**
	 * Supprimer un utilisateur.
	 * Endpoint : DELETE /utilisateurs/delete/{id}
	 *
	 * @param id Identifiant de l'utilisateur à supprimer.
	 */
	@DeleteMapping("/delete/{id}")
	public void supprimerUtilisateur(@PathVariable int id) {
		utilisateurService.deleteUtilisateur(id);
	}

	/**
	 * Récupérer tous les utilisateurs d'un rôle donné.
	 * Endpoint : GET /utilisateurs/role/{role}
	 *
	 * @param role Rôle des utilisateurs à rechercher.
	 * @return Liste des utilisateurs ayant ce rôle sous forme de DTO.
	 */
	@GetMapping("/role/{role}")
	public List<UtilisateurDTO> obtenirUtilisateursParRole(@PathVariable Role role) {
		return utilisateurService.getUtilisateursByRole(role).stream()
				.map(UtilisateurDTO::convertToDTO)
				.collect(Collectors.toList());
	}

	/**
	 * Récupérer les utilisateurs d'un département donné.
	 * Endpoint : GET /utilisateurs/departement/{departementId}
	 *
	 * @param departementId Identifiant du département.
	 * @return Liste des utilisateurs du département sous forme de DTO.
	 */
	@GetMapping("/departement/{idDepartement}")
	public List<UtilisateurDTO> obtenirUtilisateursParDepartement(@PathVariable int idDepartement) {
		Departement departement = new Departement();
		departement.setIdDepartement(idDepartement);

		return utilisateurService.getUtilisateursByDepartement(departement)
				.stream()
				.map(UtilisateurDTO::convertToDTO)
				.collect(Collectors.toList());
	}

	/**
	 * Récupérer les utilisateurs ayant un type de compteur spécifique.
	 * Endpoint : GET /utilisateurs/compteur/{typeCompteur}
	 *
	 * @param typeCompteur Type de compteur recherché.
	 * @return Liste des utilisateurs avec ce type de compteur.
	 */
	@GetMapping("/compteur/{typeCompteur}")
	public List<UtilisateurDTO> obtenirUtilisateursParTypeCompteur(@PathVariable TypeCompteur typeCompteur) {
		return utilisateurService.getUtilisateursByCompteurType(typeCompteur).stream().map(UtilisateurDTO::convertToDTO).collect(Collectors.toList());
	}
}