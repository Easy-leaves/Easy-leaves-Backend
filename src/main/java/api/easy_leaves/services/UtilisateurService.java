package api.easy_leaves.services;

import api.easy_leaves.errors.DataBaseError;
import api.easy_leaves.model.Utilisateur;
import api.easy_leaves.repository.UtilisateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service pour la gestion des utilisateurs.
 * Fournit des méthodes pour manipuler les données des utilisateurs dans la base.
 * 
 * @author Driss
 */
@Service
public class UtilisateurService {

    /**
     * Référentiel pour accéder aux données des utilisateurs dans la base de données.
     */
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    /**
     * Encodeur de mot de passe pour sécuriser les mots de passe des utilisateurs.
     */
    private PasswordEncoder passwordEncoder;

    /**
     * Constructeur pour injecter le `PasswordEncoder`.
     * @param passwordEncoder Encodeur de mot de passe utilisé pour encoder les mots de passe.
     */
    @Autowired
    public UtilisateurService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Récupérer tous les utilisateurs.
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
     * @throws DataBaseError Si aucun utilisateur n'est trouvé pour l'identifiant donné.
     */
    public Utilisateur getUtilisateurById(int id) {
        return utilisateurRepository.findById(id).orElseThrow(() -> new DataBaseError("Utilisateur introuvable"));
    }

    /**
     * Récupérer un utilisateur par son nom.
     * 
     * @param nom Nom de l'utilisateur.
     * @return L'utilisateur correspondant.
     */
    public Optional<Utilisateur> getUtilisateurByNom(String nom) {
        return utilisateurRepository.findByNom(nom);
    }

    /**
     * Récupérer un utilisateur par son prénom.
     * 
     * @param prenom Prénom de l'utilisateur.
     * @return L'utilisateur correspondant.
     */
    public Optional<Utilisateur> getUtilisateurByPrenom(String prenom) {
        return utilisateurRepository.findByPrenom(prenom);
    }

    /**
     * Récupérer un utilisateur par son email.
     * 
     * @param email Email de l'utilisateur.
     * @return L'utilisateur correspondant.
     */
    public Optional<Utilisateur> getUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    /**
     * Récupérer les utilisateurs par rôle.
     * 
     * @param role Rôle des utilisateurs.
     * @return Liste des utilisateurs correspondant au rôle.
     */
    public List<Utilisateur> getUtilisateursByRole(api.easy_leaves.enums.Role role) {
        return utilisateurRepository.findByRole(role);
    }

    /**
     * Récupérer les utilisateurs d'un département spécifique.
     * 
     * @param departement Département concerné.
     * @return Liste des utilisateurs du département.
     */
    public List<Utilisateur> getUtilisateursByDepartement(api.easy_leaves.model.Departement departement) {
        return utilisateurRepository.findByDepartement(departement);
    }

    /**
     * Créer un nouvel utilisateur.
     * 
     * @param utilisateur Objet contenant les informations de l'utilisateur à enregistrer.
     * @return L'utilisateur nouvellement créé.
     */
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        String encodedPassword = passwordEncoder.encode(utilisateur.getPassword());
        utilisateur.setPassword(encodedPassword);
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

	/**
	 * Récupérer un utilisateur avec ses absences.
	 * 
	 * @param id Identifiant de l'utilisateur.
	 * @return L'utilisateur avec ses absences.
	 */
	public Utilisateur getUtilisateurWithAbsences(int id) {
		return utilisateurRepository.findByIdWithAbsences(id);
	}

	/**
	 * Récupérer les utilisateurs ayant un compteur spécifique.
	 * 
	 * @param type Type de compteur.
	 * @return Liste des utilisateurs ayant ce compteur.
	 */
	public List<Utilisateur> getUtilisateursByCompteurType(api.easy_leaves.enums.TypeCompteur type) {
		return utilisateurRepository.findByCompteurType(type);
	}
}
