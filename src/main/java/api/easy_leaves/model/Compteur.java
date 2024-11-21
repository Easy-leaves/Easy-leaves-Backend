/**
 *
 */
package api.easy_leaves.model;

import api.easy_leaves.enums.TypeCompteur;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/** 
 * @author Nicolas LE LANNIER
 */
@Entity
@Table(name = "COMPTEUR")
public class Compteur {
	
	/** idCompteur */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCompteur;
	
	/** annee */
	private int annee;
	
	/** typeCompteur */
	private TypeCompteur typeCompteur;
	
	/** utilisateursCompteurs */
	@ManyToOne
	@JoinColumn(name = "compteurUtilisateur")
	private Utilisateur utilisateur;

	
	
	/** Constructeur
	 * @param annee
	 * @param typeCompteur
	 * @param utilisateur
	 */
	public Compteur(int annee, TypeCompteur typeCompteur, Utilisateur utilisateur) {
		super();
		this.annee = annee;
		this.typeCompteur = typeCompteur;
		this.utilisateur = utilisateur;
	}


	/** Constructeur
	 * 
	 */
	public Compteur() {
		super();
	}


	/** Getter
	 * @return the idCompteur
	 */
	public int getIdCompteur() {
		return idCompteur;
	}


	/** Setter
	 * @param idCompteur the idCompteur to set
	 */
	public void setIdCompteur(int idCompteur) {
		this.idCompteur = idCompteur;
	}


	/** Getter
	 * @return the annee
	 */
	public int getAnnee() {
		return annee;
	}


	/** Setter
	 * @param annee the annee to set
	 */
	public void setAnnee(int annee) {
		this.annee = annee;
	}


	/** Getter
	 * @return the typeCompteur
	 */
	public TypeCompteur getTypeCompteur() {
		return typeCompteur;
	}


	/** Setter
	 * @param typeCompteur the typeCompteur to set
	 */
	public void setTypeCompteur(TypeCompteur typeCompteur) {
		this.typeCompteur = typeCompteur;
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
