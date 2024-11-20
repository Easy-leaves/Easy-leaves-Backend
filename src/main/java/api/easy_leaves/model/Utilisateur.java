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
@Table(name = "UTILISATEUR")
public class Utilisateur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUtilisateur;
	
	private String nom;
	private String prenom;
	private String email;
	private int role;
	
	
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
	
	
}
