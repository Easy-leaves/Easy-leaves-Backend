package api.easy_leaves.dto;

import java.util.ArrayList;
import java.util.List;

import api.easy_leaves.enums.Role;

/*
 * Class which represent a user.
 */
public class UtilisateurDTO {
	
	/** 
	 * The user identifier. 
	 */
	private int id;
	
	/** 
	 * The last name of the user. 
	 */
	private String nom;
	
	/** 
	 * The first name of the user.
	 */
	private String prenom;
	
	/** 
	 * The user email.
	 */
	private String email;
	
	/**
	 * The role of the user.
	 */
	private Role role;
	
	/**
	 * The list of compteur identifier linked to this user.
	 */
	private List<Integer> compteurIds = new ArrayList<>();
	
	/**
	 * The departement identifier linked to this user.
	 */
	private int departementId;
	
	/**
	 * The list of absence identifier linked to this user.
	 */
	private List<Integer> absenceIds = new ArrayList<>();
	
	/**
	 * Constructor
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param role
	 * @param compteurUtilisateur
	 * @param departement
	 * @param absenceUtilisateur
	 */
	public UtilisateurDTO(String nom, String prenom, String email, Role role,
			List<Integer> compteurIds, int departementId, List<Integer> absenceIds)
	{
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.role = role;
		this.compteurIds = compteurIds;
		this.departementId = departementId;
		this.absenceIds = absenceIds;
	}

	/** 
	 * Constructor
	 */
	public UtilisateurDTO() {
		super();
	}

	/**
	 * Getter
	 * @return the user identifier.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter
	 * @param id the identifier to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter
	 * @return the last name of the user.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter
	 * @param nom the last name to set.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter
	 * @return the first name.
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Setter
	 * @param prenom the first name to set.
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Getter
	 * @return the email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter
	 * @return the list of compteur ids.
	 */
	public List<Integer> getCompteurIds() {
		return compteurIds;
	}

	/**
	 * Setter
	 * @param compteurIds the compteurIds to set
	 */
	public void setCompteurUtilisateur(List<Integer> compteurIds) {
		this.compteurIds = compteurIds;
	}

	/**
	 * Getter
	 * @return the departement identifier.
	 */
	public int getDepartement() {
		return departementId;
	}

	/** 
	 * Setter
	 * @param departementId the departementId to set
	 */
	public void setDepartement(int departementId) {
		this.departementId = departementId;
	}

	/** 
	 * Getter
	 * @return the absence identifiers linked to this user.
	 */
	public List<Integer> getAbsenceUtilisateurId() {
		return absenceIds;
	}

	/**
	 * Setter
	 * @param absenceIds the absenceIds to set
	 */
	public void setAbsenceUtilisateur(List<Integer> absenceIds) {
		this.absenceIds = absenceIds;
	}

	/**
	 * Getter
	 * @return the role of this user.
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Setter
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
}
