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
@Table(name = "DEPARTEMENT")
public class Departement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDepartement;
	
	private String libelle;

	/** Constructeur
	 * @param libelle
	 */
	public Departement(String libelle) {
		super();
		this.libelle = libelle;
	}

	/** Constructeur
	 * 
	 */
	public Departement() {
		super();
	}

	/** Getter
	 * @return the idDepartement
	 */
	public int getIdDepartement() {
		return idDepartement;
	}

	/** Setter
	 * @param idDepartement the idDepartement to set
	 */
	public void setIdDepartement(int idDepartement) {
		this.idDepartement = idDepartement;
	}

	/** Getter
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/** Setter
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
}
