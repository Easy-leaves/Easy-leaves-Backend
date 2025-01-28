/**
 *
 */
package api.easy_leaves.enums;

/**
 * Enumération représentant les différents rôles dans l'application.
 * Chaque rôle définit un niveau d'accès ou de privilège pour les utilisateurs.
 * 
 * Les rôles définis sont :
 * - ADMINISTRATEUR : Le rôle avec le plus haut niveau de privilège, généralement pour la gestion de l'application.
 * - MANAGER : Un rôle intermédiaire avec des privilèges de gestion, mais moins étendus que l'administrateur.
 * - EMPLOYE : Le rôle de base avec des privilèges limités à l'utilisation des fonctionnalités principales.
 * 
 * @author Nicolas LE LANNIER
 */
public enum Role {
    ADMINISTRATEUR,
    MANAGER,
    EMPLOYE
}

