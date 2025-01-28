/**
 *
 */
package api.easy_leaves.enums;

/**
 * Enumération représentant les différents types de compteurs d'absences dans l'application.
 * Chaque type de compteur est associé à une catégorie spécifique d'absence dont le solde doit être suivi.
 * 
 * Les types de compteurs définis sont :
 * - RTT_EMPLOYEUR : Compteur pour les RTT accordés par l'employeur.
 * - RTT_EMPLOYE : Compteur pour les RTT pris par l'employé.
 * - CONGE_PAYE : Compteur pour les congés payés.
 * 
 * @author Nicolas LE LANNIER
 */
public enum TypeCompteur {
    RTT_EMPLOYEUR,
    RTT_EMPLOYE,
    CONGE_PAYE
}
