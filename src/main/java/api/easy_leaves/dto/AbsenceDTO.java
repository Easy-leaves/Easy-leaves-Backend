package api.easy_leaves.dto;

import java.util.Date;

import api.easy_leaves.enums.Statut;
import api.easy_leaves.enums.TypeAbsence;

/**
 * Class which defines an absence.
 */
public class AbsenceDTO {

	/** 
	 * The absence identifier.
	 */
    private int id;
	
	/**
	 * The start date of an absence.
	 */
	private Date dateDebut;
	
	/**
	 * The end date of an absence.
	 */
	private Date dateFin;
	
	/**
	 * The type of the absence.
	 */
	private TypeAbsence type;
	
	/**
	 * The status of the absence.
	 */
	private Statut statut;
	
	/**
	 * The motive of the absence.
	 */
	private String motif;
	
	/**
	 * The user identifier linked to this absence.
	 */	
	private int utilisateurId;

	/** 
	 * The constructor of an absence.
	 * @param idAbsence
	 * @param dateDebut
	 * @param dateFin
	 * @param type
	 * @param statut
	 * @param motif
	 * @param utilisateur
	 */
	public AbsenceDTO(int id, Date dateDebut, Date dateFin,
			TypeAbsence type, Statut statut, String motif,
			int utilisateurId) {
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.type = type;
		this.statut = statut;
		this.motif = motif;
		this.utilisateurId = utilisateurId;
	}

	/** 
	 * The constructor of an absence.
	 */
	public AbsenceDTO() {
	}

	/** 
	 * Getter
	 * @return the Id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter
	 * @param id the Id to set.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter
	 * @return the start date.
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * Setter
	 * @param dateDebut the start date to set.
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * Getter
	 * @return the end date.
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * Setter
	 * @param dateFin the end date to set.
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * Getter
	 * @return the type of the absence.
	 */
	public TypeAbsence getType() {
		return type;
	}

	/**
	 * Setter
	 * @param type the type to set.
	 */
	public void setType(TypeAbsence type) {
		this.type = type;
	}

	/**
	 * Getter
	 * @return the status of the absence.
	 */
	public Statut getStatut() {
		return statut;
	}

	/**
	 * Setter
	 * @param statut the statut to set.
	 */
	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	/**
	 * Getter
	 * @return the motive of the absence.
	 */
	public String getMotif() {
		return motif;
	}

	/**
	 * Setter
	 * @param motif the motif to set
	 */
	public void setMotif(String motif) {
		this.motif = motif;
	}

	/**
	 * Getter
	 * @return the user id linked to this absence.
	 */
	public int getUtilisateurId() {
		return utilisateurId;
	}

	/**
	 * Setter
	 * @param utilisateur the utilisateur id to linked the absence with.
	 */
	public void setUtilisateurId(int utilisateurId) {
		this.utilisateurId = utilisateurId;
	}
}
	