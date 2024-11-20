/**
 *
 */
package api.easy_leaves.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/** 
 * @author Nicolas LE LANNIER
 */
@Entity
@Table(name = "COMPTEUR")
public class Compteur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCompteur;
	
	private int annee;
	private int typeCompteur;


	/** Constructeur
	 * @param annee
	 * @param typeCompteur
	 */
	public Compteur(int annee, int typeCompteur) {
		super();
		this.annee = annee;
		this.typeCompteur = typeCompteur;
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
	public int getTypeCompteur() {
		return typeCompteur;
	}


	/** Setter
	 * @param typeCompteur the typeCompteur to set
	 */
	public void setTypeCompteur(int typeCompteur) {
		this.typeCompteur = typeCompteur;
	}
	
}
