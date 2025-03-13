/**
 *
 */
package api.easy_leaves.enums;

/**
 * Enumération représentant les différents types d'absences possibles dans l'application.
 * Chaque type d'absence correspond à une catégorie spécifique d'absence qu'un utilisateur peut avoir.
 * 
 * Les types d'absences définis sont :
 * - RTT_EMPLOYEUR : RTT accordé par l'employeur.
 * - RTT_EMPLOYE : RTT pris par l'employé.
 * - CONGE_PAYE : Congé payé.
 * - CONGE_SANS_SOLDE : Congé sans solde.
 * - AUTRE : Toute autre forme d'absence non spécifiée par les autres types.
 * 
 * @author Nicolas LE LANNIER
 */
public enum TypeAbsence {
    RTT_EMPLOYEUR,
    RTT_EMPLOYE,
    CONGE_PAYE,
    CONGE_SANS_SOLDE,
    FERIE,
    AUTRE
}

