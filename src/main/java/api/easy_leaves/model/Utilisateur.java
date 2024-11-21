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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/** 
 * @author Nicolas LE LANNIER
 */
@Entity
@Table(name = "UTILISATEUR")
public class Utilisateur {
	
	/** idUtilisateur */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUtilisateur;
	
	/** nom */
	private String nom;
	
	/** prenom */
	private String prenom;
	
	/** email */
	private String email;
	
	/** role */
	private int role;
	
    /** compteur */
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Compteur> compteurUtilisateur = new ArrayList<>();
    
    /** departements */
	@ManyToOne
	@JoinColumn(name = "departementUtilisateur")
	private Departement departement;
	
    /** absences */
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Absence> absenceUtilisateur = new ArrayList<>();
	
	
	/** Constructeur
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param role
	 */
	public Utilisateur(String nom, String prenom, String email, int role) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.role = role;
	}


	/** Constructeur
	 * 
	 */
	public Utilisateur() {
		super();
	}


	/** Getter
	 * @return the idUtilisateur
	 */
	public int getIdUtilisateur() {
		return idUtilisateur;
	}


	/** Setter
	 * @param idUtilisateur the idUtilisateur to set
	 */
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}


	/** Getter
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}


	/** Setter
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}


	/** Getter
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}


	/** Setter
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	/** Getter
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/** Setter
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/** Getter
	 * @return the role
	 */
	public int getRole() {
		return role;
	}


	/** Setter
	 * @param role the role to set
	 */
	public void setRole(int role) {
		this.role = role;
	}


	/** Getter
	 * @return the compteurUtilisateur
	 */
	public List<Compteur> getCompteurUtilisateur() {
		return compteurUtilisateur;
	}


	/** Setter
	 * @param compteurUtilisateur the compteurUtilisateur to set
	 */
	public void setCompteurUtilisateur(List<Compteur> compteurUtilisateur) {
		this.compteurUtilisateur = compteurUtilisateur;
	}


	/** Getter
	 * @return the departement
	 */
	public Departement getDepartement() {
		return departement;
	}


	/** Setter
	 * @param departement the departement to set
	 */
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}


	/** Getter
	 * @return the absenceUtilisateur
	 */
	public List<Absence> getAbsenceUtilisateur() {
		return absenceUtilisateur;
	}


	/** Setter
	 * @param absenceUtilisateur the absenceUtilisateur to set
	 */
	public void setAbsenceUtilisateur(List<Absence> absenceUtilisateur) {
		this.absenceUtilisateur = absenceUtilisateur;
	}

	
}
