package api.easy_leaves.services;

import api.easy_leaves.model.Departement;
import api.easy_leaves.repository.DepartementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service pour la gestion des départements.
 * Permet de manipuler les données des départements dans la base de données.
 * 
 * @author Driss
 */
@Service
public class DepartementService {
	
	@Autowired
	private DepartementRepository departementRepository;
	
	/**
	 * Récupérer tous les départements.
	 * 
	 * @return Liste des départements disponibles.
	 */
	public List<Departement> getAllDepartements() {
	    return departementRepository.findAll();
	}
	
	/**
	 * Récupérer un département par son identifiant.
	 * 
	 * @param id Identifiant unique du département.
	 * @return Le département correspondant.
	 * @throws RuntimeException Si aucun département n'est trouvé pour l'identifiant donné.
	 */
	public Departement getDepartementById(int id) {
	    return departementRepository.findById(id).orElseThrow(() -> new RuntimeException("Département introuvable"));
	}
	
	/**
	 * Créer un nouveau département.
	 * 
	 * @param departement Objet contenant les informations du département à enregistrer.
	 * @return Le département nouvellement créé.
	 */
	public Departement createDepartement(Departement departement) {
	    return departementRepository.save(departement);
	}
	
	/**
	 * Mettre à jour un département existant.
	 * 
	 * @param id Identifiant du département à mettre à jour.
	 * @param departementDetails Objet contenant les nouvelles informations du département.
	 * @return Le département mis à jour.
	 * @throws RuntimeException Si le département à mettre à jour n'existe pas.
	 */
	public Departement updateDepartement(int id, Departement departementDetails) {
		Departement departement = getDepartementById(id);
		departement.setLibelle(departementDetails.getLibelle());
		return departementRepository.save(departement);
	}
	
	/**
	 * Supprimer un département.
	 * 
	 * @param id Identifiant du département à supprimer.
	 */
	public void deleteDepartement(int id) {
	    departementRepository.deleteById(id);
	}
}
