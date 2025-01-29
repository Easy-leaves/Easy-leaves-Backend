/**
 *
 */
package api.easy_leaves.enums;

/**
 * Enumération représentant les différents statuts d'une absence dans l'application.
 * Chaque statut indique l'état actuel d'une absence et son traitement.
 * 
 * Les statuts définis sont :
 * - INITIALE : Le statut par défaut, indiquant que l'absence est en attente de traitement.
 * - EN_ATTENTE_VALIDATION : L'absence est en attente de validation par un responsable.
 * - REFUSEE : L'absence a été refusée.
 * - VALIDE : L'absence a été validée et acceptée.
 * 
 * @author Nicolas LE LANNIER
 */
public enum Statut {
    INITIALE,
    EN_ATTENTE_VALIDATION,
    REFUSEE,
    VALIDEE
}

