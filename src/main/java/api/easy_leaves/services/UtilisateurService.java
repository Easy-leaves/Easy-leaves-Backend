package api.easy_leaves.services;

import api.easy_leaves.dto.UtilisateurDTO;
import api.easy_leaves.model.Absence;
import api.easy_leaves.model.Compteur;
import api.easy_leaves.model.Utilisateur;
import api.easy_leaves.repository.UtilisateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service pour la gestion des utilisateurs.
 * Fournit des méthodes pour manipuler les données des utilisateurs dans la base.
 * 
 * @author Driss
 */
@Service
public class UtilisateurService {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	/**
	 * Récupérer tous les utilisateurs.
	 * 
	 * @return Liste des utilisateurs disponibles.
	 */
	public List<Utilisateur> getAllUtilisateurs() {
		
	    return utilisateurRepository.findAll();
	}
	
	/**
	 * Récupérer un utilisateur par son identifiant.
	 * 
	 * @param id Identifiant unique de l'utilisateur.
	 * @return L'utilisateur correspondant.
	 * @throws RuntimeException Si aucun utilisateur n'est trouvé pour l'identifiant donné.
	 */
	public Utilisateur getUtilisateurById(int id) {
	    return utilisateurRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
	}
	
	/**
	 * Créer un nouvel utilisateur.
	 * 
	 * @param utilisateur Objet contenant les informations de l'utilisateur à enregistrer.
	 * @return L'utilisateur nouvellement créé.
	 */
	public Utilisateur createUtilisateur(Utilisateur utilisateur) {
	    return utilisateurRepository.save(utilisateur);
	}
	
	/**
	 * Mettre à jour un utilisateur existant.
	 * 
	 * @param id Identifiant de l'utilisateur à mettre à jour.
	 * @param utilisateurDetails Objet contenant les nouvelles informations de l'utilisateur.
	 * @return L'utilisateur mis à jour.
	 * @throws RuntimeException Si l'utilisateur à mettre à jour n'existe pas.
	 */
	public Utilisateur updateUtilisateur(int id, Utilisateur utilisateurDetails) {
		Utilisateur utilisateur = getUtilisateurById(id);
		utilisateur.setNom(utilisateurDetails.getNom());
		utilisateur.setPrenom(utilisateurDetails.getPrenom());
		utilisateur.setEmail(utilisateurDetails.getEmail());
		utilisateur.setRole(utilisateurDetails.getRole());
		return utilisateurRepository.save(utilisateur);
	}
	
	/**
	 * Supprimer un utilisateur.
	 * 
	 * @param id Identifiant de l'utilisateur à supprimer.
	 */
	public void deleteUtilisateur(int id) {
	    utilisateurRepository.deleteById(id);
	}
}
