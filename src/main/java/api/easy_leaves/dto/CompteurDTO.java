package api.easy_leaves.dto;

import api.easy_leaves.enums.TypeCompteur;
import api.easy_leaves.model.Compteur;

/**
 * Class which defines a compteur. 
 */
public class CompteurDTO {
	
	/**
	 * The identifier.
	 */
	private int id;
	
	/**
	 * Get the year of the compteur.
	 */
	private int annee;
	
	/**
	 * The type of the compteur.
	 */
	private TypeCompteur type;
	
	/** 
	 * The user identifier linked to this compteur.
	 */
	private int utilisateurId;
	
	/** 
	 * Constructor
	 * @param annee
	 * @param typeCompteur
	 * @param utilisateur
	 */
	public CompteurDTO(int idCompteur, int annee, TypeCompteur type, int utilisateurId) {
		this.id = idCompteur;
		this.annee = annee;
		this.type = type;
		this.utilisateurId = utilisateurId;
	}
	
	/** 
	 * Constructor.
	 */
	public CompteurDTO() {
	}

	/**
	 * Getter
	 * @return the compteur's identifier.
	 */
	public int getIdCompteur() {
		return id;
	}

	/**
	 * Setter
	 * @param idCompteur the idCompteur to set.
	 */
	public void setIdCompteur(int id) {
		this.id = id;
	}

	/**
	 * Getter
	 * @return the year of the compteur.
	 */
	public int getAnnee() {
		return annee;
	}

	/**
	 * Setter
	 * @param annee the year to set.
	 */
	public void setAnnee(int annee) {
		this.annee = annee;
	}

	/**
	 * Getter
	 * @return the type of the compteur.
	 */
	public TypeCompteur getTypeCompteur() {
		return type;
	}

	/**
	 * Setter
	 * @param TypeCompteur the type to set.
	 */
	public void setTypeCompteur(TypeCompteur type) {
		this.type = type;
	}

	/**
	 * Getter
	 * @return the utilisateur id.
	 */
	public int getUtilisateur() {
		return utilisateurId;
	}

	/**
	 * Setter
	 * @param utilisateur the utilisateur to set.
	 */
	public void setUtilisateur(int utilisateurId) {
		this.utilisateurId = utilisateurId;
	}
	
	/**
	 * Convert a Compteur entity into a CompteurDTO.
	 * @param compteur
	 * @return
	 */
	public static CompteurDTO convertToDTO(Compteur compteur) {
		return new CompteurDTO(
			compteur.getIdCompteur(),
			compteur.getAnnee(),
			compteur.getTypeCompteur(),
			compteur.getUtilisateur() != null ? compteur.getUtilisateur().getIdUtilisateur() : null
		);
	}
}
