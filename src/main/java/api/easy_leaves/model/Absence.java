/**
 *
 */
package api.easy_leaves.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/** 
 * @author Nicolas LE LANNIER
 */
@Entity
@Table(name = "ABSENCE")
public class Absence {

	/** idAbsence */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAbsence;
	
	/** dateDebut */
	private Date dateDebut;
	
	/** dateFin */
	private Date dateFin;
	
	/** type */
	private int type;
	
	/** statut */
	private int statut;
	
	/** motif */
	private String motif;
	
	/** utilisateursAbsences */	
	@ManyToOne
	@JoinColumn(name = "absenceUtilisateur")
	private Utilisateur utilisateur;
	
	
	/** Constructeur
	 * @param dateDebut
	 * @param dateFin
	 * @param type
	 * @param statut
	 * @param motif
	 */
	public Absence(Date dateDebut, Date dateFin, int type, int statut, String motif) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.type = type;
		this.statut = statut;
		this.motif = motif;
	}

	/** Constructeur
	 * 
	 */
	public Absence() {
		super();
	}

	/** Getter
	 * @return the idAbsence
	 */
	public int getIdAbsence() {
		return idAbsence;
	}

	/** Setter
	 * @param idAbsence the idAbsence to set
	 */
	public void setIdAbsence(int idAbsence) {
		this.idAbsence = idAbsence;
	}

	/** Getter
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/** Setter
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/** Getter
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/** Setter
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	/** Getter
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/** Setter
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/** Getter
	 * @return the statut
	 */
	public int getStatut() {
		return statut;
	}

	/** Setter
	 * @param statut the statut to set
	 */
	public void setStatut(int statut) {
		this.statut = statut;
	}

	/** Getter
	 * @return the motif
	 */
	public String getMotif() {
		return motif;
	}

	/** Setter
	 * @param motif the motif to set
	 */
	public void setMotif(String motif) {
		this.motif = motif;
	}

	/** Getter
	 * @return the utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	/** Setter
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	
	
}
