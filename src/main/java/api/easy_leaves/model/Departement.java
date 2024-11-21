/**
 *
 */
package api.easy_leaves.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/** 
 * @author Nicolas LE LANNIER
 */
@Entity
@Table(name = "DEPARTEMENT")
public class Departement {

	/** idDepartement */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDepartement;
	
	/** libelle */
	private String libelle;
	
	/** utilisateur */
    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
    private List<Utilisateur> utilisateurs = new ArrayList<>();


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

	/** Getter
	 * @return the utilisateurs
	 */
	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	/** Setter
	 * @param utilisateurs the utilisateurs to set
	 */
	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	
}
