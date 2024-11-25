package api.easy_leaves.controller;

import org.springframework.web.bind.annotation.RestController;

import api.easy_leaves.dto.DepartementDTO;
import api.easy_leaves.model.Departement;
import api.easy_leaves.services.DepartementService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author Theo
 */
@RestController
@RequestMapping("/departements")
public class DepartementController {
	private final DepartementService departementService;
	
	/**
	 * Constructeur
	 * @param absenceService
	 */
	public DepartementController(DepartementService departementService) {
		this.departementService = departementService;
	}
	
	/**
	 * Récupérer tous les départements
	 * localhost:8080/departements
	 * @return Liste des départements
	 */
	@GetMapping
	public List<DepartementDTO> obtenirTousLesDepartements() {
		return departementService.getAllDepartements().stream()
				.map(DepartementDTO::convertToDTO)
				.collect(Collectors.toList());
	}
	
	/**
	 * Récupérer un département par ID
	 * localhost:8080/departements/{id}
	 * @param id Identifiant du département
	 * @return Département correspondant à l'ID
	 */
	@GetMapping("/{id}")
	public DepartementDTO obtenirDepartementParId(@PathVariable int id) {
		return DepartementDTO.convertToDTO(departementService.getDepartementById(id));
	}
	
	/**
	 * Créer un nouveau département
	 * localhost:8080/departements/add
	 * @param departement Objet Département à créer
	 * @return Département créé
	 */
	@PostMapping("/add")
	public Departement creerDepartement(@RequestBody Departement departement) {
	    return departementService.createDepartement(departement);
	}
	
	/**
	 * Mettre à jour un département existant
	 * localhost:8080/departements/update/{id}
	 * @param id Identifiant du département à mettre à jour
	 * @param departementDetails Détails de la mise à jour
	 * @return Département mis à jour
	 */
	@PutMapping("/update/{id}")
	public Departement mettreAJourDepartement(@PathVariable int id, @RequestBody Departement departementDetails) {
	    return departementService.updateDepartement(id, departementDetails);
	}
	
	/**
	 * Supprimer un département
	 * localhost:8080/departements/delete/{id}
	 * @param id Identifiant du département à supprimer
	 * @return Message de confirmation
	 */
	@DeleteMapping("/delete/{id}")
	public void supprimerDepartement(@PathVariable int id) {
	    departementService.deleteDepartement(id);
	}
}
