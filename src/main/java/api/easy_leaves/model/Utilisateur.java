package api.easy_leaves.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import api.easy_leaves.enums.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/** 
 * @author Nicolas LE LANNIER
 */
@Entity
@Table(name = "UTILISATEUR")
public class Utilisateur implements UserDetails{
	
	/** idUtilisateur */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_utilisateur")
	private int idUtilisateur;
	
	/** nom */
	private String nom;
	
	/** prenom */
	private String prenom;
	
	/** email */
	private String email;
	
	/** mot de passe */
	private String mdp;
	
	/** role */
	private Role role;
	
	/** compteur */
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
	private List<Compteur> compteurUtilisateur = new ArrayList<>();
	
	/** departements */
	@ManyToOne
	@JoinColumn(name = "departementUtilisateur")
	@NotNull
	private Departement departement;
	
	/** absences */
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Absence> absenceUtilisateur = new ArrayList<>();	
	
	/** Constructeur
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param motDePasse
	 * @param role
	 * @param compteurUtilisateur
	 * @param departement
	 * @param absenceUtilisateur
	 */
	public Utilisateur(String nom, String prenom, String email,String motDePasse, Role role, List<Compteur> compteurUtilisateur,
			Departement departement, List<Absence> absenceUtilisateur) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdp = motDePasse;
		this.role = role;
		this.compteurUtilisateur = compteurUtilisateur;
		this.departement = departement;
		this.absenceUtilisateur = absenceUtilisateur;
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

	/** Getter
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/** Setter
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/** Method
	 * @param motDePasse
	 */
	public void setPassword(String motDePasse) {
		this.mdp = motDePasse;
	}

	/**
     * Retourne la liste des autorisations accordées à l'utilisateur.
     * Cette méthode est utilisée par Spring Security pour déterminer les rôles et permissions de l'utilisateur.
     * 
     * @return Une collection d'objets {@link GrantedAuthority} représentant les rôles de l'utilisateur.
     */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(role.name()));
	}
	
	/** Method
	 * @return the mdp
	 */
	public String getMdp() {
		return mdp;
	}

	/** Method
	 * @param mdp
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	/**
     * Retourne le mot de passe de l'utilisateur.
     * Cette méthode est utilisée par Spring Security pour la gestion de l'authentification.
     * 
     * @return Le mot de passe de l'utilisateur.
     */
    @Override
    public String getPassword() {
        return mdp;
    }

    /**
     * Retourne le nom d'utilisateur de l'utilisateur.
     * Cette méthode est utilisée par Spring Security pour l'authentification.
     * 
     * @return L'email de l'utilisateur qui sert de nom d'utilisateur.
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'objet {@link Utilisateur}.
     * Cette méthode fournit un résumé complet des informations de l'utilisateur.
     * 
     * @return Une chaîne contenant toutes les informations de l'utilisateur.
     */
	@Override
	public String toString() {
		return "Utilisateur [idUtilisateur=" + idUtilisateur + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", mdp=" + mdp + ", role=" + role + ", compteurUtilisateur=" + compteurUtilisateur
				+ ", departement=" + departement + ", absenceUtilisateur=" + absenceUtilisateur + "]";
	}
}
