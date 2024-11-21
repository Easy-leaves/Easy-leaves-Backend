package api.easy_leaves.dto;

import java.util.ArrayList;
import java.util.List;

/*
 * Class which represent a departement.
 */
public class DepartementDTO {

	/** 
	 * The departement identifier. 
	 */
	private int id;
	
	/** 
	 * The libelle.
	 */
	private String libelle;
	
	/**
	 * The list of user ids linked to this departement. 
	 */
	private List<Integer> utilisateurIds = new ArrayList<Integer>();

	/** 
	 * Constructor
	 * @param libelle
	 */
	public DepartementDTO(String libelle) {
		super();
		this.libelle = libelle;
	}

	/**
	 * Constructor
	 */
	public DepartementDTO() {
		super();
	}

	/**
	 * Getter
	 * @return the departement identifier.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter
	 * @param id the departement identifier to set.
	 */
	public void setIdDepartement(int id) {
		this.id = id;
	}

	/**
	 * Getter
	 * @return the libelle.
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Setter
	 * @param libelle the libelle to set.
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * Getter
	 * @return the list of user ids linked to this departement.
	 */
	public List<Integer> getUtilisateurIds() {
		return utilisateurIds;
	}

	/**
	 * Setter
	 * @param utilisateurs the utilisateurs to set.
	 */
	public void setUtilisateurIds(List<Integer> utilisateurIds) {
		this.utilisateurIds = utilisateurIds;
	}
}
